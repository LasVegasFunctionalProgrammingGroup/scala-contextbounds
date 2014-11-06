name := "Context Bound Examples"

scalaVersion := "2.11.4"

lazy val root = project.in(file(".")).aggregate(uiexample)

lazy val uiexample = project.in(file("uiexample"))

