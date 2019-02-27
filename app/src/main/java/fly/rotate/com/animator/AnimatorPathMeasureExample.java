package fly.rotate.com.animator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fly.rotate.com.customview.R;
import fly.rotate.com.customview.fivestar.SimpleFivePointedStar;

public class AnimatorPathMeasureExample extends AppCompatActivity implements View.OnClickListener {

    private SimpleFivePointedStar simpleFivePointedStar0, simpleFivePointedStar1, simpleFivePointedStar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_pathmeasure);
        initView();
    }

    private void initView() {
        simpleFivePointedStar0 = findViewById(R.id.simpleFivePointedStar0);
        simpleFivePointedStar1 = findViewById(R.id.simpleFivePointedStar1);
        simpleFivePointedStar2 = findViewById(R.id.simpleFivePointedStar2);

        simpleFivePointedStar0.setOnClickListener(this);
        simpleFivePointedStar1.setOnClickListener(this);
        simpleFivePointedStar2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simpleFivePointedStar0:
                simpleFivePointedStar0.startAnimator();
                break;
            case R.id.simpleFivePointedStar1:
                simpleFivePointedStar1.startAnimator();
                break;
            case R.id.simpleFivePointedStar2:
                simpleFivePointedStar2.startAnimator();
                break;
            default:
                break;
        }
    }
}
