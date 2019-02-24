package fly.rotate.com.customview.progressBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircleBarView extends View {

    private Paint rPaint;//绘制矩形的画笔
    private Paint progressPaint;//绘制圆弧的画笔
    private Paint defaultProgressPaint;//绘制圆弧的画笔
    private Paint textPaint;//绘制圆弧的画笔

    private float sweepAngle;//圆弧经过的角度

    private CircleBarAnim anim;

    public CircleBarView(Context context) {
        this(context, null);
    }

    public CircleBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public ViewPropertyAnimator animate() {
        return super.animate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rPaint = new Paint();
        rPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        rPaint.setColor(Color.RED);

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        progressPaint.setColor(Color.BLUE);
        progressPaint.setStrokeWidth(20);
        progressPaint.setAntiAlias(true);//设置抗锯齿

        defaultProgressPaint = new Paint();
        defaultProgressPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        defaultProgressPaint.setColor(Color.RED);
        defaultProgressPaint.setStrokeWidth(20);
        defaultProgressPaint.setAntiAlias(true);//设置抗锯齿

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);

        anim = new CircleBarAnim();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = 0;
        float y = 0;
        RectF rectF = new RectF(x, y, x + 300, y + 300);//建一个大小为300 * 300的正方形区域
        RectF rectF1 = new RectF(x+25, y+25, x + 275, y + 275);//建一个大小为300 * 300的正方形区域
        canvas.drawRect(rectF, rPaint);
        canvas.drawArc(rectF1, 0, 360, false, defaultProgressPaint);
        canvas.drawArc(rectF1, 0, sweepAngle, false, progressPaint);
        canvas.drawText((int) (sweepAngle / 360 * 100) + "", 150, 150, textPaint);
    }

    public class CircleBarAnim extends Animation {

        public CircleBarAnim() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            sweepAngle = interpolatedTime * 360;
            postInvalidate();
        }
    }

    /**
     * 写个方法给外部调用，用来设置动画时间
     *
     * @param time
     */
    public void setProgressNum(int time) {
        anim.setDuration(time);
        this.startAnimation(anim);
    }

}
