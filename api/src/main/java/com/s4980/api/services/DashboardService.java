package com.s4980.api.services;

import com.s4980.domain.GetDashboardResponse;
import com.s4980.domain.NewDashboardRequest;
import com.s4980.domain.NewDashboardResponse;
import com.s4980.domain.SearchDashboardResponse;
import com.s4980.api.factories.Authorizator;
import com.s4980.api.factories.RestInterfaceFactory;
import org.apache.commons.lang.StringUtils;
import com.s4980.api.rest.DashboardRest;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by MZ on 2016-01-30.
 */
public class DashboardService extends AbstractService {
    private DashboardRest dashboardRest;

    public DashboardService(Authorizator authorizator) {
        Retrofit retrofit = RestInterfaceFactory.getInterface(authorizator);
        this.dashboardRest = retrofit.create(DashboardRest.class);
        this.authorizator = authorizator;
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
