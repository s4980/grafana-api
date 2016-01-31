import com.google.gson.Gson;
import domain.Dashboard;
import domain.DashboardMetadata;
import domain.NewDashboardResponse;
import services.DashboardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by MZ on 2016-01-30.
 */
public class Main {

    public static void main(String[] args) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + "eyJrIjoiOVN5eU1EOEc2dlgwNlhEZVoyNUlPaDg5TWlsSlA1V0giLCJuIjoidGVzdDEiLCJpZCI6MX0=");

        DashboardService dashboardService = new DashboardService(headers, "http://127.0.0.1:3000/api/");

        List<DashboardMetadata> metadataList = dashboardService.searchAll();
        metadataList.forEach(dashboardMetadata -> System.out.println(dashboardService.get(dashboardMetadata.getUri())));


        List<DashboardMetadata> dashboards = dashboardService.search(new HashMap<String, String>() {{
            put("query", "Test_dashboard");
        }});

        if (!dashboards.isEmpty()) {
            DashboardMetadata metadata = dashboards.get(0);
            final Dashboard dashboard = dashboardService.get(metadata.getUri()).getDashboard();

            if (dashboard != null) {
                Integer version = dashboard.getVersion();
                System.out.printf("Version before updating dashboard %s with override flag: %s\n", dashboard.getTitle(), version);
                dashboard.setVersion(++version);
                final NewDashboardResponse newDasboardResponse = dashboardService.update(dashboard, true);
                System.out.println(newDasboardResponse);

                Dashboard dashboardUpdated = dashboardService.get(metadata.getUri()).getDashboard();
                System.out.printf("Version after updating dashboard %s: %s\n", dashboardUpdated.getTitle(), version);
            }
        }

        // create new dashboard
        Dashboard newDashboard = new Dashboard();
        final String title = "NewDashboard_" + UUID.randomUUID();
        newDashboard.setTitle(title);
        newDashboard.setOriginalTitle(title);
        Gson gson = new Gson();
        final String json = gson.toJson(newDashboard);
        final NewDashboardResponse newDasboardResponse = dashboardService.create(newDashboard, false);

        // trying to get new dashboard
        List<DashboardMetadata> newDashboards = dashboardService.search(new HashMap<String, String>() {{
            put("query", title);
        }});

        if (!newDashboards.isEmpty()) {
            System.out.println(dashboardService.get(newDashboards.get(0).getUri()).getDashboard());
        }
    }

}
