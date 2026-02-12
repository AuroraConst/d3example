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
import typings.d3.d3Strings.line
import org.scalajs.dom.Element
import typings.d3Shape.mod.Line_
import typings.d3Selection.mod.ArrayLike
import typings.d3.d3Strings.svg
import typings.d3Force.mod.Force
import typings.d3.d3Strings.map
/**
 * Main notes:
  Watch how Select[?,?,?,?] changes with "builder" operations, like data()
*/

object d3svgforcelink:

  val width = 400
  val height = 400


  def start(): Unit = 
    console.info("Starting d3svgforcelink example")

    def random = Random.nextInt(10)*width/10.0
    case class Node(id:String, x:Double = 0, y:Double = 0)
    case class Link(source:Node, target:Node)
    val nm = "1234567890"  //node map from string key to Node
      .toCharArray()
      .map{c => c.toString -> Node(s"$c",random, random)}.toMap
    val nmKeys = nm.keySet.toSeq.toJSArray

    import org.scalajs.dom.Element
    import typings.d3Selection.mod.ValueFn

    type F[DATUM] = ValueFn[Element, DATUM, Null | String | Double | Boolean | (js.Array[String | Double])]

    def f(  lambda: (d:Node) => Double): F[Node] = 
     (thisArg:Element,d:Node,index:Double,data:Any)  => {
        lambda(d).toString
    }

    def idf(lambda: (d:Node) => String): ValueFn[Element, Node, Null | String | Double | Boolean | (js.Array[String | Double])] =
     (thisArg:Element,d:Node,index:Double,data:Any)  => {
        lambda(d)
    }

    val svg = d3Mod.select(s"#${svgforcelink}")
      .attr("width", width)
      .attr("height", height)
      .style("border", "1px solid black")
      .append("g")
      .attr("transform", s"translate(${0}, ${0})")

    val node =  svg.append("g")
      .selectAll("circle")
      .data(nmKeys.map{k => nm(k)})  
      .enter()
      .append("circle")
      .attr("r", 10)
      .attr("id",idf((n:Node) => n.id))
      .attr("fill","#69b3a2")
 
    def ticked =  (thisArg:Any) => {
        node.asInstanceOf[Selection_[Element, Node, Any, Any]]
          .attr("cx", f((d:Node) => d.x))
          .attr("cy", f((d:Node) => {console.info(s"ticked: ${d.y}");d.y}))
        ()  
      }

    val sim = d3Mod.forceSimulation(nmKeys.map{k => nm(k)})  //the nodes of the simulation are the values of the node map
      .force("charge",d3Mod.forceManyBody().strength(-50).asInstanceOf[Force[Node,Unit]])  //this is how you set the charge force, using a callback to convert the node data to a forceManyBody
      .force("center",d3Mod.forceCenter(width/2,height/2).asInstanceOf[Force[Node,Unit]])  //this is how you set the center force, using a callback to convert the node data to a forceCenter
      .on("tick", ticked )



    




   
