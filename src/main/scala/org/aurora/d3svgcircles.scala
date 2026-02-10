package org.aurora

import typings.d3.mod as d3Mod

import typings.std.global.{console, window}

import scala.scalajs.js
import org.scalajs.dom.{SVGCircleElement}
import scala.collection.immutable.LazyList.cons
import typings.d3Selection.mod.EnterElement
import org.scalajs.dom.Element

object d3svgcircles:
  case class CircleData(id: Double,radius:Double, color: String, x: Double, y: Double)
  val width = 400
  val height = 400


  import js.JSConverters._
  val data = (1 to 550).map{i =>
    CircleData(i, 10 + Math.random() * 20, s"hsl(${Math.random() * 360}, 100%, 50%)", Math.random() * width, Math.random() * height)
  }.toSeq.toJSArray



  def start(): Unit = 
    console.info("Starting d3svg example")
    val svg = d3Mod.select(s"#$svgId")
      .attr("width", width)
      .attr("height", height)
      .style("border", "1px solid black")

    import org.aurora.d3.axis.*  

    val circles = svg.selectAll[SVGCircleElement, CircleData]("circle")
      .data(data)
      .join("circle" )
      .attr("cx", callback {(cd:CircleData) => cd.x })
      .attr("cy", callback {(cd:CircleData) => cd.y })
      .attr("fill", callback {(cd:CircleData) => cd.color })
      .attr("r", {(cd:CircleData) => cd.radius }.toCallback)  //alternative way to create the call back via extension method. not sure which way I like better


    import typings.d3Selection.mod.ValueFn
    import typings.d3Transition.mod.Transition_
    type F = ValueFn[js.Dynamic,Any,Unit]
    def f: F = 
      val x:F = (thisArg:js.Dynamic,x:Any,elem:Any,data:Any)  => {
        d3Mod.active(thisArg.asInstanceOf[Element])
          .transition()
          .duration(1500)
          .style("fill", "white")
          
        console.info("Axis animation complete"); 
      }
      x


    circles.asInstanceOf[TRANSITION]
        .transition()
        .duration(3000)
        .style("fill", "black")
        .on("end", f) 
    
  


 



