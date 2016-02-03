package com.s4980.grafana.api.rest.factories;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Created by MZ on 2016-02-02.
 */
@AllArgsConstructor
@Getter
public class Authorizator {

    private Map<String, String> headers;
    private String apiUrl;

}
