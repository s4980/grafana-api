package interfaces;

import domain.DashboardMetadata;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

public interface DashboardService {

    @GET("search")
    Call<List<DashboardMetadata>> searchDashbord(@QueryMap Map<String, String> options);
}
