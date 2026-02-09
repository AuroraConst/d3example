package org.aurora

import typings.d3.mod as d3Mod

import typings.std.global.{console, window}

import scala.scalajs.js
import org.scalajs.dom.{SVGCircleElement, HTMLElement}
import typings.d3Scale.mod.NumberValue
import js.JSConverters.*
import typings.d3Axis.mod.{AxisScale,AxisDomain}
import typings.d3Selection.mod.Selection_
import typings.d3Axis.mod.Axis
import org.scalajs.dom.HTMLHtmlElement
import org.scalajs.dom.SVGGElement
import org.scalajs.dom.SVGSVGElement


/**
 * Main notes:
  Watch how Select[?,?,?,?] changes with "builder" operations, like data()
*/

object d3svgsimple:


  val data = (1 to 350).map{_.toDouble}.toJSArray

  def start(): Unit = 
    console.info("Starting d3svgsimple example")
    val width = 600
    val height = 300

    
    
    val svg = d3Mod.select(s"#${org.aurora.svgsimple}")
      .attr("width", width)
      .attr("height", height)
      .style("border", "1px solid black")


    val xScale = d3Mod.scaleLinear()
      .domain(js.Array(data.min,data.max).map{_.toDouble})
      .range(js.Array(0, width-5).map{_.toDouble})


    val xAxis = d3Mod.axisBottom(xScale.toAxisScale)

    type SELECTIONTYPE = Selection_[SVGGElement|SVGSVGElement, Double, HTMLElement, Any]
    type SELECTIONANY = Selection_[SVGGElement|SVGSVGElement, Any,Any,Any]

    
    svg.append("g")
      .data(data)  //note the type changes to SELECTION_[?,?,?,?].  without this call, the type is SELECTION_[?,Nothing,?,?,?]
      .call(( s:SELECTIONTYPE, a: Axis[Double]) => 
              {
                console.info("about to call Applying axis!!!!!!!!") 
                a.apply(s.asInstanceOf[SELECTIONANY]) //this is the logic to draw the axis
              } , xAxis)






