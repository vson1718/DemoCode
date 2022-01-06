package com.vson.code;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vson.code.ceiling.CeilingEffectActivity;
import com.vson.code.slide.SlideCardActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ceiling_effect).setOnClickListener(this);
        findViewById(R.id.slide_card).setOnClickListener(this);
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
            default:
                break;
        }
    }
}