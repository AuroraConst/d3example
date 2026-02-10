package org.aurora

import typings.d3.mod as d3Mod

import typings.std.global.{console, window}

import scala.scalajs.js
import org.scalajs.dom.{SVGCircleElement}

object d3svg:
  case class CircleData(id: Double,radius:Double, color: String, x: Double, y: Double)


  import js.JSConverters._
  val data = (1 to 550).map{i =>
    CircleData(i, 10 + Math.random() * 20, s"hsl(${Math.random() * 360}, 100%, 50%)", Math.random() * window.innerWidth, Math.random() * window.innerHeight)
  }.toSeq.toJSArray



  def start(): Unit = 
    console.info("Starting d3svg example")
    val svg = d3Mod.select(s"#$svgId")
      .attr("width", s"${window.innerWidth}px")
      .attr("height", s"${window.innerHeight}px")
      .style("border", "1px solid black")

    svg.selectAll[SVGCircleElement, CircleData]("circle")
      .data(data)
      .join("circle" )
      .attr("cx", callback {(cd:CircleData) => cd.x })
      .attr("cy", callback {(cd:CircleData) => cd.y })
      .attr("fill", callback {(cd:CircleData) => cd.color })
      .attr("r", {(cd:CircleData) => cd.radius }.toCallback)  //alternative way to create the call back via extension method. not sure which way I like better





end d3svg
