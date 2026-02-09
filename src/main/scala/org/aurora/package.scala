package org


import org.scalajs.dom.{CanvasRenderingContext2D,Console}
import org.scalajs.dom.{SVGCircleElement}
import org.scalajs.dom.{SVGElement,SVGGElement,SVGSVGElement}
import scala.scalajs.js
import typings.d3Selection.mod.{ValueFn,ArrayLike}
import org.scalajs.dom.HTMLHtmlElement
import org.scalajs.dom.HTMLElement




package object aurora :
  val svgId = "d3svg"
  val deitzsvgId = "deitzd3svg"
  val canvasId = "d3canvas"

  val console = Console
  
  given CanvasRenderingContext2D = CanvasContext.context(canvasId) //see index.html
  type Array[DATUM] = js.Array[DATUM] | ArrayLike[DATUM]


  extension [DATUM,R](f: (data:DATUM) => R)
      def toCallback[SVGELEMENDATUM <: SVGElement]: ValueFn[SVGELEMENDATUM, DATUM, R] =
        (thisArg: SVGELEMENDATUM, data: DATUM, index: Double, array: Array[SVGELEMENDATUM]) => f(data)



  
  //this was hell figuring this out!!
  def callback[SVGELEMENDATUM <: SVGElement,DADATUMUM,R](f: (i:DADATUMUM)=> R): ValueFn[SVGELEMENDATUM, DADATUMUM, R] =
      (thisArg: SVGELEMENDATUM, data: DADATUMUM, index: Double, array: Array[SVGELEMENDATUM]) => f(data)


  import typings.d3Scale.mod.NumberValue

  def convert[DATUM](f: (datum:DATUM) =>Double ) :js.Function3[DATUM, Double, js.Array[DATUM], Double] = 
    (d:DATUM, v:Double, a:js.Array[DATUM]) => f(d)


  import typings.d3Selection.mod.{Selection_}
  import typings.d3Axis.mod.Axis
  
  def applyG[Domain](a:Axis[Domain]):js.Function2[Selection_[SVGGElement|SVGSVGElement, Any, Any, Any],Axis[Domain], Unit  ]  = 
    (s:Selection_[SVGGElement|SVGSVGElement, Any, Any, Any], axis:Axis[Domain]) => axis.apply(s)
    




end aurora    


  
