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

ThisBuild / scalaVersion := "3.0.0-RC1"
ThisBuild / crossScalaVersions := Seq(
  "2.12.13",
  "2.13.5",
  "3.0.0-M3",
  "3.0.0-RC1"
)

val GraalVM11 = "graalvm-ce-java11@20.3.0"

ThisBuild / githubWorkflowJavaVersions := Seq(GraalVM11)

//sbt-ci-release settings
ThisBuild / githubWorkflowTargetTags ++= Seq("v*")
ThisBuild / githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Branch("master")), RefPredicate.StartsWith(Ref.Tag("v")))
ThisBuild / githubWorkflowPublishPreamble := Seq(WorkflowStep.Use(UseRef.Public("olafurpg", "setup-gpg", "v3")))
ThisBuild / githubWorkflowPublish := Seq(WorkflowStep.Sbt(List("ci-release")))
ThisBuild / githubWorkflowEnv ++= List("PGP_PASSPHRASE", "PGP_SECRET", "SONATYPE_PASSWORD", "SONATYPE_USERNAME").map { envKey =>
  envKey -> s"$${{ secrets.$envKey }}"
}.toMap

val commonSettings = Seq(
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
