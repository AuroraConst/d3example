package org.aurora.hldesign


object NameID :
  private var id = 0
  private def nextId = id+=1;id

  def id(name:String): String = s"#${name}_$nextId"

object utils:
  extension(o:Any)
    def cleanname =
      val sn = o.getClass().getSimpleName()
      sn.indexOf("$") match
        case -1 => sn
        case i => sn.substring(0, i)

