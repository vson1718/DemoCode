package com.vson.code.ceiling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vson.code.R;
import com.vson.code.ceiling.CeilingAdapter;
import com.vson.code.ceiling.CeilingModel;
import com.vson.uikit.ceiling.CeilingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CeilingEffectActivity extends AppCompatActivity {


    private List<CeilingModel> starList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceiling_effect);
        init();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CeilingAdapter(this, starList));
        recyclerView.addItemDecoration(new CeilingItemDecoration(this));
    }

    private void init() {
        starList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 20; j++) {
                if (i % 2 == 0) {
                    starList.add(new CeilingModel("何炅" + j, "快乐家族" + i));
                } else {
                    starList.add(new CeilingModel("汪涵" + j, "天天兄弟" + i));
                }
            }
        }
    }
}