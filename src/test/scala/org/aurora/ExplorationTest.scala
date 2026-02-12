package org.aurora


object ab
class FirstTest extends BasicTest {
  def strip(s:String): String = s.stripSuffix("$")

  extension(o:Any)
    def cleanname =
      val sn = o.getClass().getSimpleName()
      sn.indexOf("$") match
        case -1 => sn
        case i => sn.substring(0, i)


      

  "access to scala module name" should {
    "work like this" in {
      
      object abcde
      
      

      info(s"${abcde.cleanname}") //prints "org.aurora.FirstTest$1a$"
      info(s"${ab.cleanname}") //prints "org.aurora.FirstTest$1a$"
    }
  }
}