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
import org.aurora.d3.axis.TRANSITION
/**
 * Main notes:
  Watch how Select[?,?,?,?] changes with "builder" operations, like data()
*/

object d3svgaxis:

  val width = 400
  val height = 400


  val data = (1 to 150).map{_.toDouble}.toJSArray
  val datay = (1 to 50).map{_.toDouble * 2}.toJSArray

  def start(): Unit = 
    console.info("Starting d3svgsimple example")

    
    
    val svg = d3Mod.select(s"#${org.aurora.svgsimple}")
      .attr("width", width)
      .attr("height", height)
      .style("border", "1px solid black")


    val xScale = d3Mod.scaleLinear()
      .domain(js.Array(data.min,data.max))
      .range(js.Array(0, width-5).map{_.toDouble})
    val yScale = d3Mod.scaleLinear()
      .domain(js.Array(datay.min,datay.max))
      .range(js.Array(height-5, 0).map{_.toDouble})  


    import org.aurora.d3.axis.*
    val xAxis = d3Mod.axisTop(xScale.toAxisScale)//.ticks(25)
    val yAxis = d3Mod.axisRight(yScale.toAxisScale)//.ticks(15)

    
    svg.append("g")
      .data(data)  //note the type changes to SELECTION_[?,?,?,?].  without this call, the type is SELECTION_[?,Nothing,?,?,?]
       .attr("transform", s"translate(0, $height)")
      .callAxis(xAxis)
      .asInstanceOf[TRANSITION]
        .transition()
        .duration(2000)
        .style("color","red")

    svg.append("g")
      .data(data)  //note the type changes to SELECTION_[?,?,?,?].  without this call, the type is SELECTION_[?,Nothing,?,?,?]
       .transform(0,0)
      //  .attr("transform", s"translate(0, $height)")
      .callAxis(yAxis)
      .asInstanceOf[TRANSITION]
        .transition()
        .duration(2000)
        .style("color","red")
    


    import typings.d3Selection.mod.ValueFn
    type F = ValueFn[Any,Any,Unit]
    val f: F = (thisArg:Any,x:Any,elem:Any,data:Any)  => console.info("Axis animation complete"); 



    svg.append("text")
      .attr("x", width/2)
      .attr("y", height/2)
      .attr("text-anchor", "middle")
      .attr("font-size", "16px")
      .text("D3 Axis Example")
      .asInstanceOf[TRANSITION]
      .transition()
        .duration(2000)
        .style("fill","blue")






