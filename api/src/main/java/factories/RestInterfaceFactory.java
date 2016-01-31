package factories;

import interceptors.HeaderInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import java.util.Map;

/**
 * Created by MZ on 2016-01-30.
 */
public class RestInterfaceFactory {

    public static Retrofit getInterface(Map<String, String> headers, String apiUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HeaderInterceptor(headers))
                .build();

        final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();
    }
}
