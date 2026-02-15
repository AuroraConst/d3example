package org.aurora.hldesign
import typings.d3.mod as d3Mod
import scala.scalajs.js
import js.JSConverters.*
import typings.d3Axis.mod.{AxisScale,AxisDomain}
import typings.d3Selection.mod.Selection_
import typings.d3Axis.mod.Axis


trait AxisTrait :
  //self type anotation to enforce inheriting from StandardSVGView
  self: StandardSVGView =>
  
  val datax = (1 to 300).map{_.toDouble}.toJSArray
  val datay = (1 to 300).map{_.toDouble * 2}.toJSArray

 
  def  xScale(xStart:Int=0, _width:Int=width)  = d3Mod.scaleLinear()
      .domain(js.Array(datax.min,datax.max))
      .range(js.Array(0, width).map{_.toDouble})

  def  yScale(_height:Int=height,yStart:Int=0) = d3Mod.scaleLinear()
      .domain(js.Array(datay.min,datay.max))
      .range(js.Array(height, 0).map{_.toDouble})  

  import org.aurora.d3utils.*
  lazy val xAxis = d3Mod.axisTop(xScale().toAxisScale)//.ticks(25)
  lazy val yAxis = d3Mod.axisRight(yScale().toAxisScale)//.ticks(15)


  lazy val showAxis =  
    //ATTEMPTING IDOMATIC SELECT JOIN PATTERN
    //TODO READ THIS GREAT SUMMARY CAREFULLY !! https://bost.ocks.org/mike/join/
    svg.selectAll("#xAxis")
      .data(datax)  //note the type changes to SELECTION_[?,?,?,?].  without this call, the type is SELECTION_[?,Nothing,?,?,?]
      .enter() //enter selection (only for the missing element
      .append("g")
      .attr("id","xAxis")
      .callAxis(xAxis)
    //   .transform(0,height)
    //   .merge(svg.selectAll("#xAxis")) //merge back to the main selection
    //   .asInstanceOf[TRANSITION]
    //     .transition()
    //     .duration(2000)
    //     .style("color","red")
    svg.append("g")
      .attr("id","xAxis")
      .data(datax)  //note the type changes to SELECTION_[?,?,?,?].  without this call, the type is SELECTION_[?,Nothing,?,?,?]
      .transform(0,height)
      .callAxis(xAxis)
      .asInstanceOf[TRANSITION]
        .transition()
        .duration(2000)
        .style("color","red")

    svg.append("g")
      .attr("id","yAxis")
      .data(datay)  //note the type changes to SELECTION_[?,?,?,?].  without this call, the type is SELECTION_[?,Nothing,?,?,?]
       .transform(0,0)
      .callAxis(yAxis)
      .asInstanceOf[TRANSITION]
        .transition()
        .duration(2000)
        .style("color","red")








