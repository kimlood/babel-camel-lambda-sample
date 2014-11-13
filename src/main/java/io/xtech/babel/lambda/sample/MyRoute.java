package io.xtech.babel.lambda.sample;

import io.xtech.babel.lambda.RouteBuilder;

public class MyRoute extends RouteBuilder {

    @Override
    public void configure() {
        from(source("file:input")).
        //define your route here
        to(sink("file:output"));
    }
}
