package fly.rotate.com.customview.fivestar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

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

    public SimpleFivePointedStar(Context context) {
        super(context);
    }

    public SimpleFivePointedStar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SimpleFivePointedStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
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
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);

        mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setColor(Color.GRAY);
        /**
         * 画出来的五角星默认闭合区域是有填充颜色的，当将画笔加上下面两个属性，则没有填充颜色，只画线
         * */
        mDefaultPaint.setStrokeWidth(4);
        mDefaultPaint.setStyle(Paint.Style.STROKE);

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
         * 此处destPath需要重置，因为getSegment方法中的dst参数中保存的Path是不断添加的
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

    public void startAnimator(){
        if(mValueAnimator!=null){
            mValueAnimator.start();
        }
    }

}
