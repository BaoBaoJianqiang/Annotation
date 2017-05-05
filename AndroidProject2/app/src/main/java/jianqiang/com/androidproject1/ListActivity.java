package jianqiang.com.androidproject1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListActivity extends Activity {

    @BindView(R.id.lst)
    ListView lst;

    @OnItemClick(R.id.lst)
    void onItemClick(int position) {
        Toast.makeText(this, "click " + position,  Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        TestAdapter adapter = new TestAdapter(this, data);
        lst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
