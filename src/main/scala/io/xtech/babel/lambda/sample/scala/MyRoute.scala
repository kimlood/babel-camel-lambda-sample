package io.xtech.babel.lambda.sample.scala

import io.xtech.babel.camel.builder.SpringRouteBuilder

/**
 * Created by babel on 11/13/14.
 */
class MyRoute extends SpringRouteBuilder{

  def configure {
    from("file:input").as[String].
      processBody(str => str.split(",").map(_.trim.toInt).reduce(_ + _))
    .choice(c => {
      c.whenBody(_ > 0).as[String].
        to("file:output1")
      c.whenBody(_ < 0).
        processBody(_ + " is negative").
        to("file:output2")

    })
  }

}
