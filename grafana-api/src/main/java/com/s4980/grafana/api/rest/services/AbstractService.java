package com.s4980.grafana.api.rest.services;

import com.s4980.grafana.api.rest.factories.Authorizator;
import lombok.Getter;

/**
 * Created by MZ on 2016-02-02.
 */
@Getter
public class AbstractService {
    protected Authorizator authorizator;

}
