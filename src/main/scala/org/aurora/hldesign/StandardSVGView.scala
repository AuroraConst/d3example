package org.aurora.hldesign


import org.aurora.HelloWorld
import com.raquo.airstream.ownership.ManualOwner
import com.raquo.laminar.api.L.{*, given}
import typings.d3.mod as d3Mod


trait StandardSVGView :
  lazy val height= 400 // standardize size here
  lazy val width = 400

  given Owner = ManualOwner() //owner needed for laminar foreach side effects
  val nameVar = HelloWorld.nameVar   //standardize access to adding event handling from form events

  //access to module name that corresponds to the id property within svg tag in index.html
  lazy val basename = 
    import utils.*
    this.cleanname

  
  //note the module name that corresponds to the id property within svg tag in index.html
  lazy val svg = d3Mod.select(s"#${basename}")
    .append("g")
    .attr("width", width)
    .attr("height", height)
    .attr("transform", s"translate(${0}, ${0})")

  def start(): Unit  =
    svg //standardize starting
  def rerender(): Unit  //standaredize rerendering

