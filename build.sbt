import org.scalajs.sbtplugin
import org.scalajs.linker.interface.ModuleSplitStyle

lazy val root = project
  .in(file("."))
  .enablePlugins(sbtplugin.ScalaJSPlugin,ScalablyTypedConverterExternalNpmPlugin)
  .settings(
    name := "d3example",
    scalaVersion := "3.5.2",
    scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature"),

    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule).withModuleSplitStyle(ModuleSplitStyle.FewestModules)
    },

    externalNpm := {
      baseDirectory.value
    },


    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      "com.raquo" %%% "laminar" % "17.2.0",
    ),
    stIgnore += "node",
    stIgnore += "typescript",
    stIgnore += "vite",
    stIgnore += "@raquo/vite-plugin-glob-resolver",
    stIgnore += "@raquo/vite-plugin-import-side-effect",
    stIgnore += "less",
    stIgnore += "extensionless",



  )
