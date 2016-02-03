import com.google.gson.Gson;
import com.s4980.domain.*;
import com.s4980.api.factories.AuthenticationFactory;
import com.s4980.api.factories.Authorizator;
import org.joda.time.DateTime;
import com.s4980.api.services.DashboardService;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by MZ on 2016-01-30.
 */
public class Main {

    public static void main(String[] args) {
        final Authorizator authorizator = AuthenticationFactory.getInstance().getTokenAuthorizator("eyJrIjoiOVN5eU1EOEc2dlgwNlhEZVoyNUlPaDg5TWlsSlA1V0giLCJuIjoidGVzdDEiLCJpZCI6MX0=", "http://127.0.0.1:3000/api/");
        DashboardService dashboardService = new DashboardService(authorizator);


        List<SearchDashboardResponse> metadataList = dashboardService.searchAll();
        metadataList.forEach(SearchDashboardResponse -> System.out.println(dashboardService.get(SearchDashboardResponse.getUri())));


        List<SearchDashboardResponse> dashboards = dashboardService.search(new HashMap<String, String>() {{
            put("query", "Test_dashboard");
        }});

        if (!dashboards.isEmpty()) {
            SearchDashboardResponse metadata = dashboards.get(0);
            final Dashboard dashboard = dashboardService.get(metadata.getUri()).getDashboard();

            if (dashboard != null) {
                // Manipulating time range
                final Time dashboardTime = dashboard.getTime();
                DateTime from = new DateTime();
                DateTime to = new DateTime();
                dashboardTime.setFrom(from.minusHours(1).toString());
                dashboardTime.setTo(to.plusDays(3).toString());

                // Manipulating dashboard version
                Integer version = dashboard.getVersion();
                System.out.printf("Version before updating dashboard %s with override flag: %s\n", dashboard.getTitle(), version);
                dashboard.setVersion(++version);

                // Updating dashboard with new values
                NewDashboardRequest newDashboardRequest = new NewDashboardRequest();
                newDashboardRequest.setDashboard(dashboard);
                newDashboardRequest.setOverwrite(true);
                final NewDashboardResponse newDasboardResponse = dashboardService.update(newDashboardRequest, true);
                System.out.println(newDasboardResponse);

                // Querying dashboard and confirming successful update
                Dashboard dashboardUpdated = dashboardService.get(metadata.getUri()).getDashboard();
                System.out.printf("Version after updating dashboard %s: %s\n", dashboardUpdated.getTitle(), version);
            }
        }

        // create new dashboard
        Dashboard newDashboard = new Dashboard();
        final String title = "NewDashboard_" + UUID.randomUUID();
        newDashboard.setTitle(title);
        newDashboard.setOriginalTitle(title);
        NewDashboardRequest newDashboardRequest = new NewDashboardRequest();
        newDashboardRequest.setDashboard(newDashboard);
        newDashboardRequest.setOverwrite(false);
        Gson gson = new Gson();
        final String json = gson.toJson(newDashboardRequest);
        final NewDashboardResponse newDasboardResponse = dashboardService.create(newDashboardRequest, false);

        // trying to get new dashboard
        List<SearchDashboardResponse> newDashboards = dashboardService.search(new HashMap<String, String>() {{
            put("query", title);
        }});

        if (!newDashboards.isEmpty()) {
            System.out.println(dashboardService.get(newDashboards.get(0).getUri()).getDashboard());
        }
    }

}
