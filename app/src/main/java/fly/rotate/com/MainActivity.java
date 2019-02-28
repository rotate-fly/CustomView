package fly.rotate.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fly.rotate.com.animator.AnimatorPathMeasureExample;
import fly.rotate.com.customview.R;
import fly.rotate.com.customview.fivestar.SimpleFivePointedStar;
import fly.rotate.com.customview.progressBar.CircleBarView;
import layout.SelfViewAnimatorExample01;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleBarView mCircleBarView;
    private Button btn1;
    private SimpleFivePointedStar mSimpleFivePointedStar;

    private TextView txt_path_measure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCircleBarView = findViewById(R.id.circle_view);
        btn1 = findViewById(R.id.btn1);
        mSimpleFivePointedStar = findViewById(R.id.simpleFivePointedStar);

        mCircleBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleBarView.setProgressNum(3000);//设置动画时间为3000毫秒，即3秒
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelfViewAnimatorExample01.class));
            }
        });

        mSimpleFivePointedStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSimpleFivePointedStar.startAnimator();
            }
        });
        txt_path_measure = findViewById(R.id.txt_path_measure);
        txt_path_measure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_path_measure:
                startActivity(new Intent(this, AnimatorPathMeasureExample.class));
                break;
            default:
                break;
        }
    }
}
