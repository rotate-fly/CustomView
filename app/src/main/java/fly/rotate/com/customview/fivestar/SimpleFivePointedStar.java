package fly.rotate.com.customview.fivestar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import fly.rotate.com.customview.R;

/**
 * Created by hzs on 2019/2/18.
 */

public class SimpleFivePointedStar extends View {

    private int widthSize;
    private Context context;
    private Paint mDefaultPaint;
    private Paint mPaint;
    private Path mDefaultPath;

    private Path destPath;
    private PathMeasure pathMeasure;

    private ValueAnimator mValueAnimator;
    private float animatorValue;

    private int paintType = 0;

    public SimpleFivePointedStar(Context context) {
        this(context, null);
    }

    public SimpleFivePointedStar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public SimpleFivePointedStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //关闭硬件加速，如果不关闭硬件加速，使用getSegment()函数无效，使用getSegment()函数进行的路径动画无效
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleFivePointedStar);
        paintType = typedArray.getInt(R.styleable.SimpleFivePointedStar_paint_style, 0);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        if (paintType == 0) {
            mPaint.setStrokeWidth(4);
            mPaint.setStyle(Paint.Style.FILL);
        }
        if (paintType == 1) {
            mPaint.setStrokeWidth(4);
            mPaint.setStyle(Paint.Style.STROKE);
        }
        if (paintType == 2) {
            mPaint.setStrokeWidth(4);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setColor(Color.GRAY);
        /**
         * 画出来的五角星默认闭合区域是有填充颜色的，当将画笔加上下面两个属性，则没有填充颜色，只画线
         * */
        if (paintType == 0) {
            mDefaultPaint.setStrokeWidth(4);
            mDefaultPaint.setStyle(Paint.Style.FILL);
        }
        if (paintType == 1) {
            mDefaultPaint.setStrokeWidth(4);
            mDefaultPaint.setStyle(Paint.Style.STROKE);
        }
        if (paintType == 2) {
            mDefaultPaint.setStrokeWidth(4);
            mDefaultPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        pathMeasure = new PathMeasure();

        mDefaultPath = new Path();
        destPath = new Path();
        initSuccessAnimator();

    }

    private void initSuccessAnimator() {
//        pathMeasure.setPath(successPath, false);
        mValueAnimator = ValueAnimator.ofFloat(0, 1f);
        mValueAnimator.setDuration(5000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
////        canvas.translate(mWidth / 2, mHeight / 2);
        /**
         * PathMeasure类中
         * public boolean getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)
         * 该方法用于截取整个Path中的某个片段，通过startD和stopD来控制截取的长度，并将截取后的Path添加到参数dst中
         * 此处destPath需要重置，因为getSegment方法中的dst参数中保存的Path是不断添加的
         * getSegment方法的startWithMoveTo参数，为true时，被截取出来的Path片段保持原状
         *                                      为false时，则会将截取出来的Path片段的起始点移动到dst的最后一个点（画线是的点坐标）
         * */
        destPath.reset();
//        destPath.lineTo(0, 0);   // destPath
        /**
         * 以五角星最上面的点为起点，绘制五角星
         * */
        int radius = widthSize / 2;
        float angle = 360 / 5;

        mDefaultPath.moveTo(radius, 0);
        mDefaultPath.lineTo(radius + (float) Math.cos((angle * 2 - 90) * Math.PI / 180) * radius, radius + (float) Math.sin((angle * 2 - 90) * Math.PI / 180) * radius);
        mDefaultPath.lineTo(radius - (float) Math.sin(angle * Math.PI / 180) * radius, radius - (float) Math.cos(angle * Math.PI / 180) * radius);
        mDefaultPath.lineTo(radius + (float) Math.sin(angle * Math.PI / 180) * radius, radius - (float) Math.cos(angle * Math.PI / 180) * radius);
        mDefaultPath.lineTo(radius - (float) Math.sin((angle * 3 - 180) * Math.PI / 180) * radius, radius + (float) Math.cos((angle * 3 - 180) * Math.PI / 180) * radius);
        mDefaultPath.close();
        canvas.drawPath(mDefaultPath, mDefaultPaint);

        pathMeasure.setPath(mDefaultPath, false);
        pathMeasure.getSegment(0, animatorValue * pathMeasure.getLength(), destPath, true);
        canvas.drawPath(destPath, mPaint);
//        canvas.restore();

    }

    public void startAnimator() {
        if (mValueAnimator != null) {
            mValueAnimator.start();
        }
    }

}
