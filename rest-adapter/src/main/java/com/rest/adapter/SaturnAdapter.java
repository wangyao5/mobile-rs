package com.rest.adapter;

import android.content.Context;
import com.rest.adapter.utils.Utils;
import java.lang.reflect.Proxy;
import javax.ws.rs.Path;

public final class SaturnAdapter {
    private String mBaseUrl;
    private String rootPath;
    private Context mContext;

    private SaturnAdapter(Context context, String baseUrl) {
        this.mContext = context;
        this.mBaseUrl = baseUrl;
    }

    public <T> T create(final Class<T> service) {
        if (!Utils.validateServiceInterface(service)) {
            throw new IllegalArgumentException("have no @Path annotation with service");
        }
        Path path = service.getAnnotation(Path.class);
        rootPath = path.value();
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new HttpHandler(mBaseUrl, rootPath, mContext));
    }

    public static final class Builder {
        private Context mContext;
        private String baseUrl;

        public Builder() {
        }

        public Builder baseUrl(String url) {
            if (url == null || "".equals(url)) {
                throw new IllegalStateException("Base URL required.");
            }
            this.baseUrl = url;
            return this;
        }

        public Builder context(Context context) {
            if (context == null) {
                throw new IllegalStateException("Context required.");
            }
            this.mContext = context;
            return this;
        }

        public SaturnAdapter build() {
            return build(mContext, baseUrl);
        }

        public SaturnAdapter build(Context context) {
            return build(context, baseUrl);
        }

        public SaturnAdapter build(Context context, String baseUrl) {
            context(context);
            baseUrl(baseUrl);

            return new SaturnAdapter(context, baseUrl);
        }
    }
}