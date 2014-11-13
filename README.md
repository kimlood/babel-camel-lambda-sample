babel-camel-lambda-sample
=========================

Sample to discover babel-camel-lambda (Improved Java DSL for Apache Camel)

In this sample, you are suppose to define a route which will 
 - receive a String containing amounts
 - sum the amounts
 - sends the sum to a media depending on if it is positive or not

The route should be define in the io.xtech.babel.lambda.sample.MyRoute class.
To get some feeling about what may be done with the babel-camel-lambda DSL, please have a look to the [Babel Lambda Test sources] (https://github.com/Crossing-Tech/babel-experimental/blob/master/babel-camel/babel-camel-lambda/src/test/java/io/xtech/babel/lambda/LambdaDSLTest.java). 
You may also have a look to the [Babel documentation] (http://crossing-tech.github.io/babel).

To Test your route, you may use the MyRouteTest JUnit test.
To try your route, you may run the Runner class.

To run the Route (the Babel Scala one), please run `mvn package  hawtio:spring`
