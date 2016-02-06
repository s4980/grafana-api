package com.s4980.grafana.api.rest.factories;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by MZ on 2016-02-06.
 */
public class AuthorizationFactoryTest {

    @Test
    public void testGetInstance() throws Exception {
        final AuthorizationFactory instance = AuthorizationFactory.getInstance();
        assertEquals(instance, AuthorizationFactory.getInstance());
    }

    @Test
    public void testGetPasswordAuthorizator() throws Exception {
        assertTrue(true);

        // TODO: Implement testGetPasswordAuthorizator
    }

    @Test
    public void testGetTokenAuthorizator() throws Exception {
        assertTrue(true);

        // TODO: Implement testGetTokenAuthorizator
    }
}