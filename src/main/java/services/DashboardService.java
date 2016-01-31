package services;

import factories.RestInterfaceFactory;
import grafana.Dashboard;
import grafana.DashboardMetadata;
import grafana.NewDashboardResponse;
import grafana.SearchDashboardResponse;
import org.apache.commons.lang.StringUtils;
import rest.DashboardRest;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by MZ on 2016-01-30.
 */
public class DashboardService {
    private DashboardRest dashboardRest;

    public DashboardService(Map<String, String> headers, String apiUrl) {
        Retrofit retrofit = RestInterfaceFactory.getInterface(headers, apiUrl);
        dashboardRest = retrofit.create(DashboardRest.class);
    }

    public List<DashboardMetadata> search(Map<String, String> queryMap) {
        try {
            Response<List<DashboardMetadata>> listResponse = dashboardRest.search(queryMap).execute();
            if (listResponse.isSuccess()) {
                return listResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<DashboardMetadata> searchAll() {
        return search(Collections.emptyMap());
    }

    public SearchDashboardResponse get(String uri) {
        try {
            final Response<SearchDashboardResponse> response = dashboardRest.get(uri).execute();
            if (response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SearchDashboardResponse getHomeDashboard() {
        return get("home");
    }

    public boolean delete(String uri) {
        try {
            final Response<String> response = dashboardRest.delete(uri).execute();
            if (response.isSuccess()) {
                response.body();
                return StringUtils.contains(response.body(), uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public NewDashboardResponse update(Dashboard dashboard, boolean overwrite) {
        NewDashboardResponse newDasboardResponse = new NewDashboardResponse();
        newDasboardResponse.setStatus("failed");
        try {
            //dashboard.setAdditionalProperty("overwrite", overwrite);
            final Response<NewDashboardResponse> response = dashboardRest.create(dashboard).execute();
            if (response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newDasboardResponse;
    }

    public NewDashboardResponse create(Dashboard dashboard, boolean overwrite) {
        // To create new dashboard we need to pass dashboard with id = null
        dashboard.setId(null);
        return update(dashboard, overwrite);
    }

}
