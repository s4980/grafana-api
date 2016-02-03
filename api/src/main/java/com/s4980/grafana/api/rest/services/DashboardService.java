package com.s4980.grafana.api.rest.services;

import com.s4980.grafana.api.commons.domain.*;
import com.s4980.grafana.api.rest.factories.Authorizator;
import com.s4980.grafana.api.rest.factories.RestInterfaceFactory;
import com.s4980.grafana.api.rest.rest.DashboardRest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by MZ on 2016-01-30.
 */
@Slf4j
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
            log.error("Error searching for dashboard", e);
        }

        return Collections.emptyList();
    }

    public List<SearchDashboardResponse> searchAll() {
        return search(Collections.<String, String>emptyMap());
    }

    public Dashboard get(String uri) {
        try {
            final Response<GetDashboardResponse> response = dashboardRest.get(uri).execute();
            if (response.isSuccess()) {
                return response.body().getDashboard();
            }
        } catch (IOException e) {
            log.error("Error searching for dashboards", e);
        }
        return null;
    }

    public Dashboard getHomeDashboard() {
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
            log.error("Error deleting dashboard", e);
        }
        return false;
    }

    public boolean update(Dashboard dashboard) {
        NewDashboardRequest request = new NewDashboardRequest();
        request.setDashboard(dashboard);
        request.setOverwrite(true);
        final Response<NewDashboardResponse> response = sendNewDashboardRequest(request);
        return null != response && response.isSuccess();
    }

    public boolean create(Dashboard dashboard) {
        NewDashboardRequest request = new NewDashboardRequest();
        // To create new dashboard we need to pass dashboard with id = null
        dashboard.setId(null);
        request.setDashboard(dashboard);
        request.setOverwrite(false);
        final Response<NewDashboardResponse> response = sendNewDashboardRequest(request);
        return null != response && response.isSuccess();
    }

    private Response<NewDashboardResponse> sendNewDashboardRequest(NewDashboardRequest request) {
        try {
            final Response<NewDashboardResponse> response = dashboardRest.create(request).execute();
            return response;
        } catch (IOException e) {
            log.error("Error sending NewDashboardRequest", e);
            return null;
        }
    }
}
