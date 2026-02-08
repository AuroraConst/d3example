package org


import org.scalajs.dom.{CanvasRenderingContext2D,Console}



package object aurora :
  val svgId = "d3svg"
  val canvasId = "d3canvas"

  val console = Console
  
  given CanvasRenderingContext2D = CanvasContext.context(canvasId) //see index.html


end aurora    
  
