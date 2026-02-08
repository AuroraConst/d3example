package org.aurora

import typings.d3.mod as d3Mod

import org.scalajs.dom.{document}
import typings.std.global.{console, document, window}
import typings.std.{ FrameRequestCallback, HTMLCanvasElement, stdStrings}


import org.scalajs.dom.{CanvasRenderingContext2D,Console}

import scala.scalajs.js
import scala.scalajs.js.|
import org.scalajs.dom.{SVGCircleElement, SVGElement}
import scala.scalajs.js.ThisFunction
import scala.scalajs.js.ThisFunction3
import typings.d3.d3Strings.th


object d3svg:
  case class CircleData(id: Double,radius:Double, color: String, x: Double, y: Double)


  val data = (1 to 550).map{i =>
    CircleData(i, 10 + Math.random() * 20, s"hsl(${Math.random() * 360}, 100%, 50%)", Math.random() * window.innerWidth, Math.random() * window.innerHeight)
  }.toSeq

  import js.JSConverters._
  val dataAsJsArray = data.toJSArray

  //TODO I don't think I need context for svg
  def start(): Unit = 
    val svg = d3Mod.select(s"#$svgId")
      .attr("width", s"${window.innerWidth}px")
      .attr("height", s"${window.innerHeight}px")
      .style("border", "1px solid black")

    val width  = window.innerWidth
    val height = window.innerHeight
    val size   = width min height


    import typings.d3Selection.mod.{ValueFn}

    val x = (thisArg: SVGCircleElement, cd: CircleData, index: Double, array: js.Array[CircleData]) => cd.x

    def callback[SVGELEMENT,DATUM,R](f: (i:DATUM)=> R): ValueFn[SVGELEMENT, DATUM, R] =
        (thisArg: SVGELEMENT, data: DATUM, index: Double, array: js.Array[SVGELEMENT] | typings.d3Selection.mod.ArrayLike[SVGELEMENT]) => f(data)


    val a = svg.selectAll[SVGCircleElement, CircleData]("circle")
      .data(dataAsJsArray)
      .join("circle" )
      .attr("cx", callback((cd:CircleData) => cd.x) )
      .attr("cy", callback((cd:CircleData) => cd.y) )
      .attr("fill", callback((cd:CircleData) => cd.color) )
      .attr("r", callback((cd:CircleData) => cd.radius) )



end d3svg
