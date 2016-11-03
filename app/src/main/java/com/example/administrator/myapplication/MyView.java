package com.example.administrator.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zjj on 2016/10/31.
 * ctrl+alt+f 提全局
 * ctrl+alt+v 局部变量
 */

public class MyView extends View {
    private Paint paint;
    private float r;
    private float dr;
    private int c;
    private int dc;
    int width;
    int height;
    String content;
    //
    public MyView(Context context) {
        this(context, null);
    }

    //布局中
    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        //R.styleable.MyView
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);
        r = typedArray.getFloat(R.styleable.MyView_radius, 50);
        dr = typedArray.getFloat(R.styleable.MyView_dr, 100);
        c =   typedArray.getColor(R.styleable.MyView_color, Color.RED);
        dc = typedArray.getColor(R.styleable.MyView_dc, Color.YELLOW);
       content = typedArray.getString(R.styleable.MyView_content);
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) (2 * dr) + getPaddingLeft() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (2 * dr) + getPaddingBottom() + getPaddingTop();
        }
        //这个方法一定要写(否则,就没有设置)
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //此处不能忘记设置画笔.不能忘记使用已经定义的参数.否则无效果
        paint.setColor(dc);
        canvas.drawCircle(width/2, height/2, dr, paint);
        paint.setColor(c);
        canvas.drawCircle(width/2, height/2, r, paint);
        Rect rect=new Rect();
        paint.getTextBounds(content,0,content.length(),rect);
    }

}
