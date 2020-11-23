name := "ir"

version := "0.1"

scalaVersion := "2.13.4"

lazy val ir = (project in file(".")).settings(
	libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test
)