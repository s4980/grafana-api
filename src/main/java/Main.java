import domain.DashboardMetadata;
import services.DashboardService;

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

        DashboardService dashboardService = new DashboardService(headers, "http://127.0.0.1:3000/api/");

        List<DashboardMetadata> metadataList = dashboardService.searchDashboard(new HashMap<>());
        metadataList.forEach(dashboardMetadata -> System.out.println(dashboardService.getDashboard(dashboardMetadata.getUri())));
    }

}
