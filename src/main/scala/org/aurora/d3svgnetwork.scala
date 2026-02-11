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


    case class Node(id:String, x:Double = 0, y:Double = 0)
    case class Link(source:Node, target:Node)
    def random = Random.nextInt(10)*width/10.0
    val nm = "ABCDEFGHIJKLMNOPQRTUVWXYZabcdefg"
      .toCharArray()
      .map{c => c.toString -> Node(s"$c",random, random)}.toMap
    val nmKeys = nm.keySet.toSeq.toJSArray
    console.info(s"nodeMap: $nm")
    val links = Seq(
      Link(nm("A"), nm("B")),
      Link(nm("A"), nm("C")),
      Link(nm("B"), nm("D")),
      Link(nm("C"), nm("D"))
    )


    import org.aurora.d3.axis.*
    
    val svgG = d3Mod.select(s"#${org.aurora.svgnetwork}")
      .attr("width", width)
      .attr("height", height)
      .style("border", "1px solid black")
      .append("g")
      .attr("transform", s"translate(${0}, ${0})")

    val nodeGroups = svgG.
       selectAll[SVGCircleElement, String]("circle")
        .data(nmKeys)
        .join("g")

    //each node group will contain a circle and text element
    nodeGroups
      .append("circle")
      .attr("cx", callback((d:String) =>nm(d).x))
      .attr("cy", callback((d:String) =>nm(d).y))
      .attr("r", 10)
      .attr("fill", "steelblue")


    nodeGroups
      .append("text")
        .attr("text-anchor", "middle")
        .attr("x", callback((d:String) =>nm(d).x))
        .attr("y", callback((d:String) =>nm(d).y))
        .attr("dy", ".35em") //this is to center the text vertically in the circle
        .attr("stroke", "black")
        .attr("fill", "yellow")
        .text(callback((d:String) =>{d })) //this is how you set the text of the circle to the node id, using a callback to convert the data to text
      

    // val simulation = d3Mod.forceSimulation()










