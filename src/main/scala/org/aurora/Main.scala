package org.aurora

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@find/**/HelloWorld.less", JSImport.Namespace)
@js.native private object Stylesheet extends js.Object

val _ = Stylesheet // Use import to prevent DCE

@main def main(): Unit = {

  //draws animated rotating globe and random lines around it 
  // val d3ctx = CanvasContext.context("d3canvas")
  d3example.start
  d3svg.start()
  

  //draws blue rectangle

  renderOnDomContentLoaded(
    container = dom.document.querySelector("#app"),
    rootNode = {
      div(
        cls("Main"),
        h1("Laminar Template"),
        HelloWorld(),
      )
    }
  )
}
