package com.s4980.grafana.api.rest.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MZ on 2016-02-02.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationFactory {

    private static AuthorizationFactory INSTANCE;

    public static AuthorizationFactory getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new AuthorizationFactory();
        }
        return INSTANCE;
    }

    public Authorizator getPasswordAuthorizator(String username, String password, String apiUrl) {
        final String encodeToString = Base64.getEncoder().encodeToString(String.format("%s:%s", username, password).getBytes());
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", String.format("Basic %s", encodeToString));
        return new Authorizator(headers, apiUrl);
    }

    public Authorizator getTokenAuthorizator(String apiToken, String apiUrl) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", String.format("Bearer %s", apiToken));
        return new Authorizator(headers, apiUrl);
    }
}
