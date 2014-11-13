package io.xtech.babel.lambda.sample;

import io.xtech.babel.lambda.sample.scala.*;
import io.xtech.babel.lambda.sample.scala.MyRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by babel on 11/12/14.
 */
public class Runner {


     public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new MyRoute());
        context.start();
        Thread.sleep(60000);
        context.stop();
        System.out.println("Bye Bye");
    }
}
