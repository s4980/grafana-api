package rest;

import domain.DashboardMetadata;
import domain.NewDashboardRequest;
import domain.NewDashboardResponse;
import domain.SearchDashboardResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface DashboardRest {

    @GET("search")
    Call<List<DashboardMetadata>> search(@QueryMap Map<String, String> options);

    @GET("dashboards/{slug}")
    Call<SearchDashboardResponse> get(@Path("slug") String slug);

    @DELETE("dashboards/{slug}")
    Call<String> delete(@Path("slug") String slug);

    @Headers({
            "Accept: application/json"
    })
    @POST("dashboards/db")
    Call<NewDashboardResponse> create(@Body NewDashboardRequest dashboard);

}
