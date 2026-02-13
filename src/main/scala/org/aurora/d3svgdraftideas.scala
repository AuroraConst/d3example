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

  override def rerender(): Unit = ???

  def start(): Unit = 
    console.info(s"Starting $nameid example")
    
    // background
    showBackground("yellow")
    showAxis

    def showBackground(color:String) = 
      svg
      .append("rect")
      .attr("width", width)
      .attr("height", height)
      .style("fill", color)








