package com.rest.adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

public class HttpHandler implements InvocationHandler {
    private String mBaseUrl;
    private String rootPath;
    private RequestQueue mRequestQueue;

    private Annotation method;
    private String path;

    private Map<String, Object> queryParams;

    private Class<?> returnType;
    private Object resultObject;

    public HttpHandler(String baseUrl, String rootPath, RequestQueue requestQueue) {
        this.mBaseUrl = baseUrl;
        this.rootPath = rootPath;
        this.mRequestQueue = requestQueue;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = method.getAnnotations();
        returnType = method.getReturnType();
//        String json = "[{\"a\":\"b\"}]";
        initMethod(annotations);

        StringRequest request = new StringRequest(Request.Method.GET, mBaseUrl, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                android.util.Log.v("yaowang", "response :" + response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                android.util.Log.v("yaowang", "error :" + error.toString());
            }
        });
        mRequestQueue.add(request);

//        try {
//            String result = future.get(100, TimeUnit.SECONDS);
//            resultObject = new Gson().fromJson(result, returnType);
//            android.util.Log.d("yaowang", result);
//        } catch (InterruptedException e) {
//            android.util.Log.d("yaowang", "InterruptedException");
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            android.util.Log.d("yaowang", "ExecutionException");
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            android.util.Log.d("yaowang", "TimeoutException");
//        }

        return resultObject;
    }

    public void initMethod(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof GET || annotation instanceof POST || annotation instanceof DELETE || annotation instanceof PUT) {
                method = annotation;
            }

            if (annotation instanceof Path) {
                path = ((Path) annotation).value();
            }
        }
    }

}
