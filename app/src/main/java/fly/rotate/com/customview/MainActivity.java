package fly.rotate.com.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fly.rotate.com.customview.progressBar.CircleBarView;

public class MainActivity extends AppCompatActivity {

    private CircleBarView mCircleBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCircleBarView = findViewById(R.id.circle_view);

        mCircleBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleBarView.setProgressNum(3000);//设置动画时间为3000毫秒，即3秒
            }
        });
    }
}
