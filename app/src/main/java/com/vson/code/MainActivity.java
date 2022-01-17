package com.vson.code;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vson.code.ceiling.CeilingEffectActivity;
import com.vson.code.slide.SlideCardActivity;
import com.vson.network.callback.HttpCallback;
import com.vson.network.callback.ICallBack;
import com.vson.network.request.OkHttpRequest;
import com.vson.xutils.XUtils;
import com.vson.xutils.annation.BindActivityRes;
import com.vson.xutils.annation.BindView;
import com.vson.xutils.annation.OnClick;
import com.vson.xutils.annation.OnLongClick;


import java.util.HashMap;


@SuppressLint("NonConstantResourceId")
@BindActivityRes(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ceiling_effect)
    private Button ceilingEffectBnt;
    @BindView(R.id.slide_card)
    private Button slideCardBnt;
    @BindView(R.id.network)
    private Button networkBnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XUtils.inject(this);
    }

    @OnClick({R.id.ceiling_effect, R.id.slide_card, R.id.network})
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

    @OnLongClick({R.id.ceiling_effect, R.id.slide_card, R.id.network})
    public boolean OnLongClick(View view) {
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
        return false;
    }
}