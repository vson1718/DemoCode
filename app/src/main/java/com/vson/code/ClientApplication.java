package com.vson.code;

import android.app.Application;

import com.vson.network.IHttpRequest;
import com.vson.network.annotation.BindOkHttp;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class ClientApplication extends Application {

}
