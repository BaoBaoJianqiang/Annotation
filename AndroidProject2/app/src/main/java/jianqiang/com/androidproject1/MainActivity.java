package jianqiang.com.androidproject1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.btnClick)
    Button btnClick;

    @OnClick(R.id.btnClick)
    void click() {
        tvTitle.setText(R.string.app_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        tvTitle.setText("jianqiang");
    }
}
