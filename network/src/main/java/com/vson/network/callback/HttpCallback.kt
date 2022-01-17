package com.vson.network.callback

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType


abstract class HttpCallback<Result> : ICallBack {

    override fun onSuccess(result: String?) {
        val clz = analysisClassInfo(this);
        val objResult: Result = Gson().fromJson(result, clz) as Result;
        onSuccess(objResult)
    }

    abstract fun onSuccess(result: Result)

    private fun analysisClassInfo(any: Any): Class<*> {
        // getGenericSuperclass();
        // 可以得到包含原始类型，参数化类型，数组，类型变量，基本数据类型
        val getType = any.javaClass.genericSuperclass
        val params = (getType as ParameterizedType).actualTypeArguments
        return params[0] as Class<*> // <>里面只有一个，所以取0号元素即可
    }
}