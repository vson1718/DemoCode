package com.vson.xutils;

import android.app.Activity;
import android.util.Log;

import com.vson.xutils.annation.BindActivityRes;
import com.vson.xutils.annation.BindView;
import com.vson.xutils.annation.OnBaseAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class XUtils {

    private static final String TAG = "XUtils";

    public static void inject(Object object) {
        if (!(object instanceof Activity)){
           throw  new IllegalArgumentException("XUtils.inject()方法 暂时只能使用在Activity 子类上，其他类没有开发...");
        }
        injectActivityRes(object);
        injectBindView(object);
        injectActionEvent(object);
    }

    /**
     * 绑定xml 到Activity中
     *
     * @param object Activity
     */
    private static void injectActivityRes(Object object) {
        Class<?> activityClass = object.getClass();
        BindActivityRes bindActivityRes = activityClass.getAnnotation(BindActivityRes.class);
        if (null == bindActivityRes) {
            Log.d(TAG, "ContentView is null ");
            return;
        }
        int layoutId = bindActivityRes.value();
        try {
            Method setContentViewMethod = activityClass.getMethod("setContentView", int.class);
            setContentViewMethod.invoke(object, layoutId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把布局事件到 Activity中去
     *
     * @param object Activity 的 this
     */
    private static void injectBindView(Object object) {
        Class<?> activityClass = object.getClass();
        Field[] fields = activityClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            BindView bindView = field.getAnnotation(BindView.class);
            if (null == bindView) {
                Log.d(TAG, "BindView is null");
                continue;
            }
            int viewIdRes = bindView.value();
            try {
                Method findViewById = activityClass.getMethod("findViewById", int.class);
                Object resultView = findViewById.invoke(object, viewIdRes);
                field.set(object, resultView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 兼容版本的事件代码
     * 把布局事件到 Activity中去
     *
     * @param object Activity 的 this
     */
    private static void injectActionEvent(Object object) {
        Class<?> activityClass = object.getClass();
        Method[] declaredMethods = activityClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                OnBaseAction onBaseAction = annotationType.getAnnotation(OnBaseAction.class);
                if (null == onBaseAction) {
                    Log.d(TAG, "onBaseAction is null");
                    continue;
                }
                //寻找被onBaseAction 注解的三要素信息
                String setNameListener = onBaseAction.setNameListener();
                Class setObjectListener = onBaseAction.setObjectListener();
                String callBackMethod = onBaseAction.callBackMethod();
                try {
                    // 由于上面无法明确 子注解   annotationType获取到子注解
                    // 获取 @OnClick(R.id.bt_t1) value == R.id.bt_t1
                    Method valueMethod = annotationType.getDeclaredMethod("value");
                    valueMethod.setAccessible(true);
                    int[] viewIdResList = (int[]) valueMethod.invoke(annotation);
                    if (viewIdResList == null) {
                        Log.d(TAG, "viewIdResList is null");
                        continue;
                    }
                    for (int i = 0; i < viewIdResList.length; i++) {
                        int viewIdRes = viewIdResList[i];
                        //获取View
                        Method findViewById = activityClass.getMethod("findViewById", int.class);
                        Object resultView = findViewById.invoke(object, viewIdRes);
                        //拿到View调用设置监听的方法对象
                        Method mViewMethod = resultView.getClass().getMethod(setNameListener, setObjectListener);
                        Object proxy = Proxy.newProxyInstance(setObjectListener.getClassLoader(),
                                new Class[]{setObjectListener},
                                new InvocationHandler() {
                                    @Override
                                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                        return declaredMethod.invoke(object, args);
                                    }
                                });
                        mViewMethod.invoke(resultView, proxy);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}















