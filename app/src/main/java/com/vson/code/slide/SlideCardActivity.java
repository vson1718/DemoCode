package com.vson.code.slide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.vson.code.R;
import com.vson.uikit.slide.CardConfig;
import com.vson.uikit.slide.SlideCardCallback;
import com.vson.uikit.slide.SlideCardLayoutManager;

import java.util.List;

public class SlideCardActivity extends AppCompatActivity {
    private RecyclerView rv;
    private SlideCardAdapter adapter;
    private List<SlideCardBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_card);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new SlideCardLayoutManager());
        mDatas = SlideCardBean.initDatas();
        adapter = new SlideCardAdapter(this,mDatas);
        rv.setAdapter(adapter);
        // 初始化数据
        CardConfig.initConfig(this);
        // 创建 ItemTouchHelper ，必须要使用 ItemTouchHelper.Callback
        ItemTouchHelper.Callback callback = new SlideCardCallback(rv, adapter, mDatas);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        // 绑定rv
        helper.attachToRecyclerView(rv);
    }
}