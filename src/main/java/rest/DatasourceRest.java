package rest;

import grafana.Datasource;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by MZ on 2016-01-31.
 */
public interface DatasourceRest {

    @GET("datasources")
    Call<Datasource> getAll();

    @GET("datasources/{id}")
    Call<Datasource> getAll(@Path("id") int id);
}
