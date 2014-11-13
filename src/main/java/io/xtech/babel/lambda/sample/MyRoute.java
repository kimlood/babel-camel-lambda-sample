package io.xtech.babel.lambda.sample;

import io.xtech.babel.lambda.RouteBuilder;

public class MyRoute extends RouteBuilder {

    @Override
    public void configure() {
        //define your route here
        from(source("file:input")).as(String.class).
                processBody((str) -> {
                    final String[] amounts = str.split(",");
                    int acc = 0;
                    for (String amount : amounts) {
                        acc += Integer.parseInt(amount.trim());
                    }
                    return acc;
                }).choice(c -> {
            c.when(x -> x > 0).
                    as(String.class).to(sink("file:output1"));
            c.when(x -> x < 0).
                    processBody((i) -> i + " is negative").to(sink("file:output2"));
        });
    }

}
