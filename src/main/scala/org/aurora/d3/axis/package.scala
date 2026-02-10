package org.aurora.d3

import org.scalajs.dom.{SVGElement,SVGGElement,SVGSVGElement,SVGCircleElement}
import scala.scalajs.js
import typings.d3Selection.mod.{ValueFn,ArrayLike}
import org.scalajs.dom.{HTMLHtmlElement,HTMLElement}
import typings.d3Scale.mod.{ScaleLinear_,NumberValue}
import typings.d3Axis.mod.{AxisScale,AxisDomain,Axis}
import typings.d3Axis.mod.Axis
import typings.d3Transition.mod.Transition_
import typings.d3Selection.mod.Selection_
package object axis :
  val temp = "x"
  
  extension [T] (s:ScaleLinear_[T,Nothing,Nothing])  
    def toAxisScale: AxisScale[T] = s.asInstanceOf[AxisScale[T]]


  
  type SELECTIONTYPE[DATUM] = Selection_[SVGGElement|SVGSVGElement, DATUM, HTMLElement, Any]
  type SELECTIONANY = Selection_[SVGGElement|SVGSVGElement, Any,Any,Any]
  type TRANSITION = Transition_[js.Dynamic, Any, Any, Any]
  

  extension [DATUM](s:Selection_[SVGGElement|SVGSVGElement,DATUM,HTMLElement,Any])  
    def applyAxis(axis:Axis[DATUM]): Unit = 
      axis.apply(s.asInstanceOf[SELECTIONANY])

    def callAxis(axis:Axis[DATUM]): Selection_[SVGGElement|SVGSVGElement,DATUM,HTMLElement,Any] = 
      s.call( (sel:SELECTIONTYPE[DATUM], a:Axis[DATUM]) => a.apply(sel.asInstanceOf[SELECTIONANY]) //this is the logic to draw the axis
       , axis)

    def transform(x:Int,y:Int)  = s.attr("transform", s"translate($x, $y)")


  // extension [SVGE,DATUM](s:Selection_[SVGE,DATUM,Nothing,Nothing])  
  //   def transition() = s.asInstanceOf[TRANSITION[DATUM]].transition()

