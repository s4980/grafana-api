package services;

import domain.DashboardMetadata;
import domain.Dashbord;
import factories.RestInterfaceFactory;
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

    public List<DashboardMetadata> searchDashboard(Map<String, String> queryMap) {
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

    public Dashbord getDashboard(String uri) {
        try {
            final Response<Dashbord> response = dashboardRest.get(uri).execute();
            if (response.isSuccess()) {
                final Dashbord dashboard = response.body();
                return dashboard;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
