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
import scala.util.Random
/**
 * Main notes:
  Watch how Select[?,?,?,?] changes with "builder" operations, like data()
*/

object d3svgnetwork:

  val width = 400
  val height = 400


  def start(): Unit = 
    console.info("Starting d3svgnetwork example")


    case class Node(id:String)
    case class Link(source:Node, target:Node)

    val nm = "ABCD".toCharArray().map{c => c.toString -> Node(c.toString)}.toMap
    val nmKeys = nm.keySet.toSeq.toJSArray
    console.info(s"nodeMap: $nm")
    val links = Seq(
      Link(nm("A"), nm("B")),
      Link(nm("A"), nm("C")),
      Link(nm("B"), nm("D")),
      Link(nm("C"), nm("D"))
    )


    import org.aurora.d3.axis.*
    
    val svg = d3Mod.select(s"#${org.aurora.svgnetwork}")
      .attr("width", width)
      .attr("height", height)
      .style("border", "1px solid black")
      .append("g")
      .attr("transform", s"translate(${0}, ${0})")

    val initNodes = svg.
       selectAll[SVGCircleElement, String]("circle")
        .data(nmKeys)
        .join("circle")
        .attr("cx", callback((d:String) =>{Random.nextInt(10)*40}.toString()))
        .attr("cy", callback((d:String) =>{Random.nextInt(10)*40}.toString()))
        .attr("r", 20)
        .attr("fill", "steelblue")


    initNodes
      // .append("g")    
      .append("text")
        .attr("text-anchor", "middle")
        .attr("dy", ".35em") //this is to center the text vertically in the circle
        .attr("stroke", "black")
        .attr("fill", "yellow")
        .text("HEaaaaaaaaaaaaaaY")
        // .text(callback((d:String) =>{console.info(s"hey $d");  s"HEY$d" })) //this is how you set the text of the circle to the node id, using a callback to convert the data to text
      

    // val simulation = d3Mod.forceSimulation()










