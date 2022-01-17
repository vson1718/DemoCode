package com.vson.network.request

import com.vson.network.IHttpRequest
import com.vson.network.callback.ICallBack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class OkHttpRequest : IHttpRequest {

    private val mOkHttpClient: OkHttpClient by lazy {
        OkHttpClient()
    }

    private fun realRequest(request: Request, iCallBack: ICallBack) {
        mOkHttpClient.run {
            newCall(request).enqueue(responseCallback = object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val result: String = response.body!!.string()
                    if (response.isSuccessful) {
                        MainScope().launch(Dispatchers.Main) {
                            iCallBack.onSuccess(result)
                        }
                    } else {
                        MainScope().launch(Dispatchers.Main) {
                            iCallBack.onFailure(result)
                        }
                    }

                }

                override fun onFailure(call: Call, e: IOException) {
                    MainScope().launch(Dispatchers.Main) {
                        iCallBack.onFailure("onFailure ${e.message}")
                    }
                }
            })
        }
    }

    private fun appendBody(params: Map<String, Any>?): RequestBody {
        val body: FormBody.Builder = FormBody.Builder()
        if (params?.isEmpty() == true) {
            return body.build()
        }
        params?.forEach {
            body.add(it.key, it.value.toString());
        }
        return body.build()
    }

    override fun post(url: String, params: Map<String, Any>?, iCallBack: ICallBack) {
        val requestBody: RequestBody = appendBody(params)
        val request: Request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        realRequest(request, iCallBack)
    }

    override fun get(url: String, iCallBack: ICallBack) {
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()
        realRequest(request, iCallBack)
    }

}