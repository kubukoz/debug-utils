inThisBuild(
  List(
    organization := "com.kubukoz",
    homepage := Some(url("https://github.com/kubukoz/debug-utils")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
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
  scalaVersion := "2.12.11",
  crossScalaVersions := Seq("2.12.11", "2.13.1"),
  name := "debug-utils",
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )
)

val debugUtils =
  project.in(file(".")).settings(name := "debug-utils", commonSettings)
