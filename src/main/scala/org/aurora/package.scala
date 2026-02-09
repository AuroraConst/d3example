package org


import org.scalajs.dom.{CanvasRenderingContext2D,Console}
import org.scalajs.dom.{SVGElement,SVGGElement,SVGSVGElement,SVGCircleElement}
import scala.scalajs.js
import typings.d3Selection.mod.{ValueFn,ArrayLike}
import org.scalajs.dom.{HTMLHtmlElement,HTMLElement}
import typings.d3Scale.mod.{ScaleLinear_,NumberValue}
import typings.d3Axis.mod.{AxisScale,AxisDomain}


package object aurora :
  //these are the ids of the dom elements in index.html
  val svgId       = "d3svg"
  val deitzsvgId  = "deitzd3svg"
  val canvasId    = "d3canvas"
  val svgsimple   = "d3svgsimple"

  val console = Console


  extension(i:Int)  
    def toNumberValue: NumberValue = 
      val nv:NumberValue = i.toDouble; 
      nv

  extension [T] (s:ScaleLinear_[T,Nothing,Nothing])  
    def toAxisScale: AxisScale[T] = s.asInstanceOf[AxisScale[T]]

    

  
  given CanvasRenderingContext2D = CanvasContext.context(canvasId) //see index.html
  type Array[DATUM] = js.Array[DATUM] | ArrayLike[DATUM]


  extension [DATUM,R](f: (data:DATUM) => R)
      def toCallback[SVGELEMENDATUM <: SVGElement]: ValueFn[SVGELEMENDATUM, DATUM, R] =
        (thisArg: SVGELEMENDATUM, data: DATUM, index: Double, array: Array[SVGELEMENDATUM]) => f(data)

  //this was hell figuring this out!!
  def callback[SVGELEMENDATUM <: SVGElement,DADATUMUM,R](f: (i:DADATUMUM)=> R): ValueFn[SVGELEMENDATUM, DADATUMUM, R] =
      (thisArg: SVGELEMENDATUM, data: DADATUMUM, index: Double, array: Array[SVGELEMENDATUM]) => f(data)



  def convert[DATUM](f: (datum:DATUM) =>Double ) :js.Function3[DATUM, Double, js.Array[DATUM], Double] = 
    (d:DATUM, v:Double, a:js.Array[DATUM]) => f(d)


    
end aurora    


  
