package org.aurora

import typings.d3.mod as d3Mod

import typings.std.global.{console, window}

import scala.scalajs.js
import js.JSConverters.*
import typings.d3Axis.mod.{AxisScale,AxisDomain}
import typings.d3Selection.mod.Selection_
import typings.d3Axis.mod.Axis
/**
 * Main notes:
  Watch how Select[?,?,?,?] changes with "builder" operations, like data()
*/
import org.aurora.hldesign.{StandardSVGView,AxisTrait}

/**
  * drafting how to abstract svg and drawing axis for charts
  */
object d3svgdraftideas extends AxisTrait with StandardSVGView :

  // override lazy val width = 100
  // override lazy val height = 100

  override def rerender(): Unit = ???

  override def start(): Unit = 
    console.info(s"Starting $basename example")
    
    // background
    showBackground("cyan")
    showAxis
    showText("join,enter,exit: https://bost.ocks.org/mike/join/")

    def showBackground(color:String) = 
      svg
      .append("rect")
      .attr("width", width)
      .attr("height", height)
      .style("fill", color)


    def showText(text: String) = 
      import org.aurora.d3utils
      svg.append("text")
      .attr("x", width/2)
      .attr("y", height/2)
      .attr("text-anchor", "middle")
      .attr("font-size", "16px")
      .text(s"$text")
      .asInstanceOf[d3utils.TRANSITION]
      .transition()
        .duration(2000)
        .style("fill","blue")
  








