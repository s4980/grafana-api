package factories;

import interceptors.HeaderInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by MZ on 2016-01-30.
 */
public class RestInterfaceFactory {

    public static Retrofit getInterface(Authorizator authorizator) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HeaderInterceptor(authorizator.getHeaders()))
                .build();

        final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return new Retrofit.Builder()
                .baseUrl(authorizator.getApiUrl())
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();
    }
}
