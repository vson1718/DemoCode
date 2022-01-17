package com.vson.network.callback

interface ICallBack {
    fun onSuccess(result: String?)
    fun onFailure(e:String?)
}