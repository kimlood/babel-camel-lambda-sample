/*
 *
 *   Copyright 2010-2014 Crossing-Tech SA, EPFL QI-J, CH-1015 Lausanne, Switzerland.
 *   All rights reserved.
 *
 * ==================================================================================
 */

package io.xtech.babel.lambda.sample;

import io.xtech.babel.lambda.sample.MyRoute;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;

import org.junit.Test;

public class MyRouteTest {

    @Test
    public void routeTest() throws Exception {
        final String input1 = "10,20,30";
        final String input2 = "10,20,-40";

        io.xtech.babel.lambda.RouteBuilder routeBuilder = new MyRoute();
        RouteBuilder routeBuilderHelper = new RouteBuilder() {
            @Override
            public void configure() {
                from("file:output1").to("mock:output1");
                from("file:output2").to("mock:output2");
                from("direct:input").to("file:input");
            }
        };

        DefaultCamelContext camelContext = new DefaultCamelContext();
        routeBuilderHelper.addRoutesToCamelContext(camelContext);
        camelContext.addRoutes(routeBuilder.getRouteBuilder());
        camelContext.start();

        final MockEndpoint mockEndpoint1 = (MockEndpoint) camelContext.getEndpoint("mock:output1");
        final MockEndpoint mockEndpoint2 = (MockEndpoint) camelContext.getEndpoint("mock:output2");
        mockEndpoint1.expectedBodiesReceived("60");
        mockEndpoint2.expectedBodiesReceived("-10 is negative");

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:input", input1);
        producerTemplate.sendBody("direct:input", input2);

        mockEndpoint1.assertIsSatisfied();
        mockEndpoint2.assertIsSatisfied();

        camelContext.shutdown();
    }

}
