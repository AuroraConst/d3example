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
  d3canvas.start

  //draws circles in svg
  d3svg.start()

  org.aurora.deitzfacade.d3svgchart.start()

  // d3svgchart.start()


  //when dom is loaded creates basic form
  renderOnDomContentLoaded(
    container = dom.document.querySelector("#app"),
    rootNode = {
      div(
        cls("Main"),
        h1("Laminar Template (scroll down to see d3 examples)"),
        HelloWorld(),
      )
    }
  )
}
