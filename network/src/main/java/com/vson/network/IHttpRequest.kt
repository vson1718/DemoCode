package com.vson.network

import com.vson.network.callback.ICallBack

interface IHttpRequest {

    fun post(url: String, params: Map<String, Any>?, iCallBack: ICallBack)

    fun get(url: String, iCallBack: ICallBack)
}