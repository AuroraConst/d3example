package org.aurora

import typings.d3.mod as d3Mod
import typings.d3Array.mod as d3ArrayMod
import typings.d3Axis.mod as d3AxisMod

import typings.std.global.{console, window }

import scala.scalajs.js
import js.JSConverters._
import typings.d3Axis.mod.{Axis,AxisScale,AxisDomain}
import typings.d3Scale.mod.{ScaleLinear_}

object d3svgchart:
  case class Data(side: String,year:Int, miles: Double, gas: Double)
  val driving = Seq(
    Data("left", 1956, 3683.6965, 2.3829 ),
    Data("right",  1957, 3722.7648, 2.4026 ),
    Data("bottom",  1958, 3776.8595, 2.2539 ),
    Data("top",  1959, 3912.0962, 2.3079 ),
    Data("right",  1960, 3942.1488, 2.2658 ),
    Data("bottom",  1961, 3984.2224, 2.2526 ),
    Data("right",  1962, 4089.4064, 2.2158 ),
    Data("bottom",  1963, 4230.6536, 2.1237 ),
    Data("bottom",  1964, 4383.9219, 2.1039 ),
    Data("bottom",  1965, 4546.2059, 2.1368 ),
    Data("top",  1966, 4681.4425, 2.1421 ),
    Data("bottom",  1967, 4837.716, 2.1408 ),
    Data("right",  1968, 5048.0841, 2.1263 ),
    Data("right",  1969, 5216.3787, 2.0737 ),
    Data("right",  1970, 5384.6732, 2.0118 ),
    Data("bottom",  1971, 5652.1412, 1.9316 ),
    Data("bottom",  1972, 5979.7145, 1.8737 ),
    Data("right",  1973, 6160.0301, 1.9026 ),
    Data("left",  1974, 5946.6566, 2.3447 ),
    Data("bottom",  1975, 6117.9564, 2.3079 ),
    Data("bottom",  1976, 6400.4508, 2.3237 ),
    Data("right",  1977, 6634.861, 2.3592 ),
    Data("bottom",  1978, 6890.308, 2.2288 ),
    Data("left",  1979, 6755.0714, 2.6829 ),
    Data("left",  1980, 6670.9241, 3.2974 ),
    Data("right",  1981, 6743.0503, 3.2961 ),
    Data("right",  1982, 6836.2134, 2.9197 ),
    Data("right",  1983, 6938.3921, 2.6566 ),
    Data("right",  1984, 7127.7235, 2.475 ),
    Data("right",  1985, 7326.0706, 2.3618 ),
    Data("left",  1986, 7554.4703, 1.7605 ),
    Data("top",  1987, 7776.8595, 1.7553 ),
    Data("bottom",  1988, 8089.4064, 1.6842 ),
    Data("left",  1989, 8395.9428, 1.7473 ),
    Data("top",  1990, 8537.1901, 1.8763 ),
    Data("right",  1991, 8528.1743, 1.7776 ),
    Data("right",  1992, 8675.432, 1.6855 ),
    Data("left",  1993, 8843.7265, 1.5974 ),
    Data("bottom",  1994, 8906.837, 1.5842 ),
    Data("bottom",  1995, 9144.2524, 1.5987 ),
    Data("top",  1996, 9183.3208, 1.6737 ),
    Data("right",  1997, 9405.71, 1.6461 ),
    Data("bottom",  1998, 9577.0098, 1.3881 ),
    Data("right",  1999, 9688.2044, 1.4987 ),
    Data("top",  2000, 9706.2359, 1.8947 ),
    Data("left",  2001, 9685.1991, 1.7658 ),
    Data("bottom",  2002, 9802.4042, 1.6381 ),
    Data("right",  2003, 9853.4936, 1.8592 ),
    Data("left",  2004, 9991.7355, 2.1421 ),
    Data("left",  2005, 10054.846, 2.5329 ),
    Data("right",  2006, 10030.8039, 2.7934 ),
    Data("right",  2007, 10012.7724, 2.9487 ),
    Data("left",  2008, 9871.5252, 3.3066 ),
    Data("bottom",  2009, 9652.1412, 2.3776 ),
    Data("left",  2010, 9592.0361, 2.6066 )
  ).toJSArray 

  val colummns = js.Array("side", "year", "miles", "gas")

  import js.JSConverters.*
  import typings.d3Scale.mod.NumberValue
  

  def start(): Unit = 
    
    // Declare the chart dimensions and margins.
    val width = 928;
    val height = 720;
    val marginTop = 20;
    val marginRight = 30;
    val marginBottom = 30;
    val marginLeft = 40;

    extension (d:Double) 
      def toNumberValue(): NumberValue = 
        val n:NumberValue = d
        n
    val x : ScaleLinear_[NumberValue,NumberValue,Nothing]  = d3Mod.scaleLinear()
      .domain(driving.map{_.miles.toNumberValue()}).nice()
      .range(js.Array(marginLeft.toNumberValue(), (width - marginRight).toNumberValue()))

    val y = d3Mod.scaleLinear()
      .domain(driving.map{_.gas.toNumberValue()}).nice()  
      .range(js.Array(height- marginBottom, marginTop))

    val line = d3Mod.line[Data]()
      .curve(d3Mod.curveCatmullRom)
      .x(convert((d:Data) => d.miles))
      .y(convert((d:Data) => d.gas))

    val svg= d3Mod.select(s"#$svgId")
      .attr("width", s"${width}px")
      .attr("height", s"${height}px")
      .attr("viewBox", s"0 0 ${width} ${height}")
      .attr("style", "max-width: 100%; height: auto;")
      .style("border", "1px solid black")


    val xscale:Axis[NumberValue] = d3Mod.axisBottom(x.asInstanceOf[AxisScale[NumberValue]])
    console.info(s"xscale")
    svg.append("g")
      .attr("transform", s"translate(0,${height - marginBottom})")
      // .call(applyG(xscale) )
      // .call(  (a:Any) => ())




    








    // def length() = line(driving) //TODO: FINISH












    // console.info("Starting d3svg example"),
    // val svg = d3Mod.select(s"#$svgId"),
    //   .attr("width", s"${window.innerWidth ),px"),
    //   .attr("height", s"${window.innerHeight ),px"),
    //   .style("border", "1px solid black"),

    // svg.selectAll[SVGCircleElement, CircleData]("circle"),
    //   .data(data),
    //   .join("circle" ),
    //   .attr("cx", callback {(cd:CircleData), => cd.x  ),),
    //   .attr("cy", callback {(cd:CircleData), => cd.y  ),),
    //   .attr("fill", callback {(cd:CircleData), => cd.color  ),),
    //   .attr("r", {(cd:CircleData), => cd.radius  ),.toCallback),





end d3svgchart
