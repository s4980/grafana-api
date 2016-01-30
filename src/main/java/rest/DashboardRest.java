package rest;

import domain.DashboardMetadata;
import domain.Dashbord;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

public interface DashboardRest {

    @GET("search")
    Call<List<DashboardMetadata>> search(@QueryMap Map<String, String> options);

    @GET("dashboards/{slug}")
    Call<Dashbord> get(@Path("slug") String slug);
}
