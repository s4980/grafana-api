package rest;

import grafana.Dashboard;
import grafana.DashboardMetadata;
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
    Call<Dashboard> get(@Path("slug") String slug);
}
