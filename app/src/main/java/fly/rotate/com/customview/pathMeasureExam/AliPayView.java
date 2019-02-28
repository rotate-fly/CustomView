package fly.rotate.com.customview.pathMeasureExam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class AliPayView extends View {

    private Paint mPaint;
    private Path mDstPath,mCirclePath;
    private float mCentX,mCentY,mRadius;

    public AliPayView(Context context) {
        this(context, null);
    }

    public AliPayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AliPayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AliPayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);

        mDstPath = new Path();
        mCirclePath = new Path();

        mCirclePath.addCircle(mCentX,mCentY,mRadius,Path.Direction.CW);


    }
}
