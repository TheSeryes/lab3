package ca.bart.frgu.democustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


class CustomView extends View
{

    //private final Paint BLUE_OUTLINE = new Paint();

    private Paint outline = new Paint();
    private Paint outlineLong = new Paint();
    private Paint outlineMid = new Paint();

    private Paint outlineGuide = new Paint();
    private Paint outlineGuidelittle = new Paint();




    private float cx, cy, radius;
    private int time = 120;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomView, defStyle, 0);
        int color;


        try
        {
            color = a.getColor(R.styleable.CustomView_color, Color.BLUE);
        } finally {
            a.recycle();
        }

        outline.setColor(color);
        outline.setStyle(Paint.Style.STROKE);
        outline.setStrokeWidth(10);

        outlineMid.setColor(color);
        outlineMid.setStyle(Paint.Style.STROKE);
        outlineMid.setStrokeWidth(5);

        outlineLong.setColor(color);
        outlineLong.setStyle(Paint.Style.STROKE);
        outlineLong.setStrokeWidth(2);

        outlineGuide.setColor(color);
        outlineGuide.setStyle(Paint.Style.STROKE);
        outlineGuide.setStrokeWidth(15);

        outlineGuidelittle.setColor(color);
        outlineGuidelittle.setStyle(Paint.Style.STROKE);
        outlineGuidelittle.setStrokeWidth(15);

    }

    public void setColor(int color)
    {
        outline.setColor(color);
        invalidate();
    }

    public void setTime(int time)
    {
        this.time = time;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        cx = w / 2;
        cy = h / 2;
        radius = Math.min(cx, cy);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        canvas.drawCircle(cx, cy, radius, outline);

        double angle = Math.toRadians(time * 6 - 90);
        canvas.drawLine(cx, cy,
                cx + 0.5f * radius * (float) Math.cos(angle),
                cy + 0.5f * radius * (float) Math.sin(angle),
                outline);

        double tAngleLong = Math.toRadians(time * 6 - 120);
        canvas.drawLine(cx, cy,
                cx + 0.9f * radius * (float) Math.cos(tAngleLong),
                cy + 0.9f * radius * (float) Math.sin(tAngleLong),
                outlineLong);

        double tAngleMid = Math.toRadians(time * 6 - 30);
        canvas.drawLine(cx, cy,
                cx + 0.75f * radius * (float) Math.cos(tAngleMid),
                cy + 0.75f * radius * (float) Math.sin(tAngleMid),
                outlineMid);


        int currentTime = 0;
        for(int i = 0 ; i <= 24; i++)
        {
            setTime(currentTime);

            if( i % 2 == 0)
            {
                double tAngleGuid = Math.toRadians(time * 3 - 30);
                canvas.drawLine(cx + radius * (float) Math.cos(tAngleGuid),
                        cy + radius * (float) Math.sin(tAngleGuid),
                        cx + 0.9f * radius * (float) Math.cos(tAngleGuid),
                        cy + 0.9f * radius * (float) Math.sin(tAngleGuid),
                        outlineGuide);
            }
            else
            {
                double tAngleGuid2 = Math.toRadians(time * 3 - 60);
                canvas.drawLine(cx + radius * (float) Math.cos(tAngleGuid2),
                        cy + radius * (float) Math.sin(tAngleGuid2),
                        cx + 0.95f * radius * (float) Math.cos(tAngleGuid2),
                        cy + 0.95f * radius * (float) Math.sin(tAngleGuid2),
                        outlineGuidelittle);
            }

            currentTime += 5;
        }
    }
}
