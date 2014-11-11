package io.xtech.babel.lambda.sample;

import io.xtech.babel.lambda.RouteBuilder;

public class MyRoute extends RouteBuilder {

    @Override
    public void configure() {
        //define your route here
        from(source("direct:input")).as(String.class).
                processBody((str) -> {
                    final String[] amounts = str.split(",");
                    int acc = 0;
                    for (String amount : amounts) {
                        acc += Integer.parseInt(amount);
                    }
                    return acc;
                }).choice(c -> {
            c.when(x -> x > 0).to(sink("mock:output1"));
            c.when(x -> x < 0).to(sink("mock:output2"));
        });
    }
}
