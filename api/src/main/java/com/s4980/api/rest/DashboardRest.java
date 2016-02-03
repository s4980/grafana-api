package com.s4980.api.rest;

import java.util.List;
import java.util.Map;

import com.s4980.domain.GetDashboardResponse;
import com.s4980.domain.NewDashboardRequest;
import com.s4980.domain.NewDashboardResponse;
import com.s4980.domain.SearchDashboardResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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
