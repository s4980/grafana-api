package com.s4980.grafana.api.rest.interceptors;

import lombok.AllArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by MZ on 2016-01-30.
 */
@AllArgsConstructor
public class HeaderInterceptor implements Interceptor {
    private final Map<String, String> headers;

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        for (String key : headers.keySet()) {
            builder.header(key, headers.get(key));
        }

        return chain.proceed(builder.build());
    }
}
