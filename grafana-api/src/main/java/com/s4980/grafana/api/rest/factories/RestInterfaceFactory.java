package com.s4980.grafana.api.rest.factories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.s4980.grafana.api.rest.interceptors.HeaderInterceptor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by MZ on 2016-01-30.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestInterfaceFactory {

    public static Retrofit getInterface(Authorizator authorizator) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HeaderInterceptor(authorizator.getHeaders()))
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();

        return new Retrofit.Builder()
                .baseUrl(authorizator.getApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
