package fly.rotate.com.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fly.rotate.com.customview.progressBar.CircleBarView;
import layout.SelfViewAnimatorExample01;

public class MainActivity extends AppCompatActivity {

    private CircleBarView mCircleBarView;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCircleBarView = findViewById(R.id.circle_view);
        btn1 = findViewById(R.id.btn1);

        mCircleBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleBarView.setProgressNum(3000);//设置动画时间为3000毫秒，即3秒
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SelfViewAnimatorExample01.class));
            }
        });
    }
}
