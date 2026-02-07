package org.aurora

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@find/**/HelloWorld.less", JSImport.Namespace)
@js.native private object Stylesheet extends js.Object

val _ = Stylesheet // Use import to prevent DCE

@main def main(): Unit = {

  val canvas = dom.document.getElementById("examplecanvas").asInstanceOf[dom.html.Canvas]
  type Ctx2D = dom.CanvasRenderingContext2D
  val ctx = canvas.getContext("2d").asInstanceOf[Ctx2D]
  ctx.fillStyle = "blue"
  ctx.fillRect(10, 10, 100, 100)
  

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
