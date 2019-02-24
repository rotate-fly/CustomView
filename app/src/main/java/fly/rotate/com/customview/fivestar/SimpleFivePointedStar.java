package fly.rotate.com.customview.fivestar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hzs on 2019/2/18.
 */

public class SimpleFivePointedStar extends View {

    private int widthSize;
    private Context context;
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = widthSize/2;
        float angle = 360/5;
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        Path mPath = new Path();
        mPath.moveTo(radius,0);
        mPath.lineTo(radius+(float) Math.cos((angle*2-90)*Math.PI / 180)*radius,radius+(float)Math.sin((angle*2-90)*Math.PI / 180)*radius);
        mPath.lineTo( radius-(float)Math.sin(angle*Math.PI / 180)*radius,radius-(float)Math.cos(angle*Math.PI / 180)*radius);
        mPath.lineTo( radius+(float)Math.sin(angle*Math.PI / 180)*radius,radius-(float)Math.cos(angle*Math.PI / 180)*radius);
        mPath.lineTo( radius-(float)Math.sin((angle*3-180)*Math.PI / 180)*radius,radius+(float)Math.cos((angle*3-180)*Math.PI / 180)*radius);
        mPath.close();
        canvas.drawPath(mPath,mPaint);
    }
}
