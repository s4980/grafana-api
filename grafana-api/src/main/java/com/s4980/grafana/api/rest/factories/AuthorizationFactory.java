package com.s4980.grafana.api.rest.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MZ on 2016-02-02.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationFactory {

    private static AuthorizationFactory INSTANCE;

    /**
     * Returns singleton instance of AuthorizationFactory
     *
     * @return
     */
    public static AuthorizationFactory getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new AuthorizationFactory();
        }
        return INSTANCE;
    }

    /**
     * Returns Authorizator for user/password access
     *
     * @param username
     * @param password
     * @param apiUrl
     * @return
     */
    public Authorizator getPasswordAuthorizator(String username, String password, String apiUrl) {
        final String encodeToString = DatatypeConverter.printBase64Binary(String.format("%s:%s", username, password).getBytes());
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", String.format("Basic %s", encodeToString));
        return new Authorizator(headers, apiUrl);
    }

    /**
     * Returns Authorizator for token access
     *
     * @param apiToken
     * @param apiUrl
     * @return
     */
    public Authorizator getTokenAuthorizator(String apiToken, String apiUrl) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", String.format("Bearer %s", apiToken));
        return new Authorizator(headers, apiUrl);
    }
}
