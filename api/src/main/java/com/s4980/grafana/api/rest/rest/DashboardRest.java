package com.s4980.grafana.api.rest.rest;

import com.s4980.grafana.api.commons.domain.GetDashboardResponse;
import com.s4980.grafana.api.commons.domain.NewDashboardRequest;
import com.s4980.grafana.api.commons.domain.NewDashboardResponse;
import com.s4980.grafana.api.commons.domain.SearchDashboardResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface DashboardRest
{
    @GET("search")
    Call<List<SearchDashboardResponse>> search(@QueryMap Map<String, String> options);

    @GET("dashboards/{slug}")
    Call<GetDashboardResponse> get(@Path("slug") String slug);

    @DELETE("dashboards/{slug}")
    Call<String> delete(@Path("slug") String slug);

    @Headers({"Accept: application/json"})
    @POST("dashboards/db")
    Call<NewDashboardResponse> create(@Body NewDashboardRequest dashboard);

}
