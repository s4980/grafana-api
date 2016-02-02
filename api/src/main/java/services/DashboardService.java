package services;

import domain.GetDashboardResponse;
import domain.NewDashboardRequest;
import domain.NewDashboardResponse;
import domain.SearchDashboardResponse;
import factories.RestInterfaceFactory;
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

    public List<SearchDashboardResponse> search(Map<String, String> queryMap) {
        try {
            Response<List<SearchDashboardResponse>> listResponse = dashboardRest.search(queryMap).execute();
            if (listResponse.isSuccess()) {
                return listResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<SearchDashboardResponse> searchAll() {
        return search(Collections.emptyMap());
    }

    public GetDashboardResponse get(String uri) {
        try {
            final Response<GetDashboardResponse> response = dashboardRest.get(uri).execute();
            if (response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GetDashboardResponse getHomeDashboard() {
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

    public NewDashboardResponse update(NewDashboardRequest dashboardRequest, boolean overwrite) {
        NewDashboardResponse newDasboardResponse = new NewDashboardResponse();
        newDasboardResponse.setStatus("failed");
        try {
            //dashboard.setAdditionalProperty("overwrite", overwrite);
            final Response<NewDashboardResponse> response = dashboardRest.create(dashboardRequest).execute();
            if (response.isSuccess()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newDasboardResponse;
    }

    public NewDashboardResponse create(NewDashboardRequest dashboardRequest, boolean overwrite) {
        // To create new dashboard we need to pass dashboard with id = null
        dashboardRequest.getDashboard().setId(null);
        return update(dashboardRequest, overwrite);
    }

}
