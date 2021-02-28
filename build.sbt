inThisBuild(
  List(
    organization := "com.kubukoz",
    homepage := Some(url("https://github.com/kubukoz/debug-utils")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "kubukoz",
        "Jakub Kozłowski",
        "kubukoz@gmail.com",
        url("https://kubukoz.com")
      )
    )
  )
)

val commonSettings = Seq(
  scalaVersion := "3.0.0-M3",
  crossScalaVersions := Seq("2.12.13", "2.13.5", "3.0.0-M3", "3.0.0-RC1"),
  name := "debug-utils",
  libraryDependencies ++= {
    if (!isDotty.value)
      Seq(
        "org.scala-lang" % "scala-reflect" % scalaVersion.value
      )
    else Nil
  },
  Test / scalacOptions -= "-Xfatal-warnings"
)

val debugUtils =
  project.in(file(".")).settings(name := "debug-utils", commonSettings)
