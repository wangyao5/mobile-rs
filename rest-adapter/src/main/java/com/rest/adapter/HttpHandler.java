package com.rest.adapter;


import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.rest.adapter.http.Request;
import com.rest.adapter.http.VolleyError;
import com.rest.adapter.http.VolleyLog;
import com.rest.adapter.http.toolbox.StringRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class HttpHandler implements InvocationHandler {
    private String mBaseUrl;
    private String rootPath;
    private Context mContext;

    private int mHttpMethod = Request.Method.GET;
    private String path;

    private Type returnType;
    private Object resultObject;

    private Map<String, String> mHeaderParams;
    private Map<String, String> mFormParams;
    private Map<String, String> mQueryParams;

    public HttpHandler(String baseUrl, String rootPath, Context context) {
        this.mBaseUrl = baseUrl;
        this.rootPath = rootPath;
        this.mContext = context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        returnType = method.getGenericReturnType();
        init(method, args);

        StringRequest request = new StringRequest(mContext, mHttpMethod, finalUrl()){
            @Override
            public Map<String, String> getHeaders() {
                return mHeaderParams;
            }

            public Map<String, String> getParams() {
                return mFormParams;
            }
        };
        String result = "";
        try {
            result = request.request();
        } catch (VolleyError volleyError) {
            VolleyLog.e("vollyError" + volleyError.toString(), volleyError);
        }

        resultObject = new Gson().fromJson(result, $Gson$Types.canonicalize(returnType));

        return resultObject;
    }

    public void init(Method method, Object[] args) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof GET) {
                mHttpMethod = Request.Method.GET;
            }

            if (annotation instanceof POST){
                mHttpMethod = Request.Method.POST;
            }

            if (annotation instanceof DELETE){
                mHttpMethod = Request.Method.DELETE;
            }

            if (annotation instanceof PUT){
                mHttpMethod = Request.Method.PUT;
            }

            if (annotation instanceof Path) {
                path = ((Path) annotation).value();
            }
        }


        mHeaderParams = new HashMap<>();
        mFormParams = new HashMap<>();
        mQueryParams = new HashMap<>();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        int argIndex = 0;
        for (Annotation[] parameterAnnos : parameterAnnotations){

            for (Annotation parameterAnno : parameterAnnos){
                if (parameterAnno instanceof HeaderParam){
                    mHeaderParams.put(((HeaderParam) parameterAnno).value(), args[argIndex].toString());
                }

                if (parameterAnno instanceof FormParam){
                    mFormParams.put(((FormParam) parameterAnno).value(), args[argIndex].toString());
                }

                if (parameterAnno instanceof PathParam){
                    StringBuilder replaceSb = new StringBuilder();
                    replaceSb.append("\\{");
                    replaceSb.append(((PathParam) parameterAnno).value().trim());
                    replaceSb.append("\\}");
                    path = path.replaceAll(replaceSb.toString(), args[argIndex].toString());
                }

                if (parameterAnno instanceof QueryParam){
                    mQueryParams.put(((QueryParam) parameterAnno).value(), args[argIndex].toString());
                }
            }
            argIndex++;
        }
    }

    private String finalUrl(){
        StringBuilder sb = new StringBuilder();
        if (mQueryParams.size() >= 0){
            Set<String> keySets = mQueryParams.keySet();
            String[] keys = new String[keySets.size()];
            keySets.toArray(keys);
            int size = keys.length;
            for (int index = 0; index < size; index++){
                String key = keys[index];
                if (index == 0){
                    sb.append("?").append(key).append("=").append(mQueryParams.get(key));
                } else {
                    sb.append("&").append(key).append("=").append(mQueryParams.get(key));
                }
            }
        }
        return mBaseUrl + rootPath + path + sb.toString();
    }

}
