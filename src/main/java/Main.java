import domain.DashboardMetadata;
import factories.RestInterfaceFactory;
import interfaces.DashboardService;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MZ on 2016-01-30.
 */
public class Main {
    public static void main(String[] args) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + "eyJrIjoiOVN5eU1EOEc2dlgwNlhEZVoyNUlPaDg5TWlsSlA1V0giLCJuIjoidGVzdDEiLCJpZCI6MX0=");
        final Retrofit retrofit = RestInterfaceFactory.getInterface(headers, "http://localhost:3000/api/");

        DashboardService dashboardService = retrofit.create(DashboardService.class);

        Map<String, String> queryMap = new HashMap<>();
//        queryMap.put("title", "Test_dashboard");
        queryMap.put("starred", "false");
        try {
            Response<List<DashboardMetadata>> listResponse = dashboardService.searchDashbord(queryMap).execute();
            if (listResponse.isSuccess()) {
                listResponse.body().forEach(System.out::println);
                listResponse.body().forEach(dashboardMetadata -> System.out.println(dashboardMetadata.getUri()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
