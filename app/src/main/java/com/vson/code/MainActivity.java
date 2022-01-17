package com.vson.code;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vson.code.ceiling.CeilingEffectActivity;
import com.vson.code.slide.SlideCardActivity;
import com.vson.network.callback.HttpCallback;
import com.vson.network.callback.ICallBack;
import com.vson.network.request.OkHttpRequest;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ceiling_effect).setOnClickListener(this);
        findViewById(R.id.slide_card).setOnClickListener(this);
        findViewById(R.id.network).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ceiling_effect:
                startActivity(new Intent(this, CeilingEffectActivity.class));
                break;
            case R.id.slide_card:
                startActivity(new Intent(this, SlideCardActivity.class));
                break;
            case R.id.network:
                String url = "https://v.juhe.cn/historyWeather/citys";
                HashMap<String, Object> params = new HashMap<>();
                // https://v.juhe.cn/historyWeather/citys?&province_id=2&key=bb52107206585ab074f5e59a8c73875b
                params.put("province_id", "2");
                params.put("key", "bb52107206585ab074f5e59a8c73875b");
                new OkHttpRequest().post(url, params, new HttpCallback<ResponseData>() {
                    @Override
                    public void onFailure(@Nullable String e) {
                        Toast.makeText(MainActivity.this, e, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(ResponseData responseData) {
                        Toast.makeText(MainActivity.this, responseData.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}