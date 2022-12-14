name := """little_country_crafts"""
organization := "littlecountrycrafts"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.6.0",
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "littlecountrycrafts.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "littlecountrycrafts.binders._"

import com.typesafe.sbt.packager.docker.DockerChmodType

dockerChmodType := DockerChmodType.UserGroupWriteExecute

dockerExposedPorts ++= Seq(9000, 9001)

dockerBaseImage := "eclipse-temurin:11"

Docker/packageName := sys.env.getOrElse("JOB_NAME", "little-country-crafts")

Universal/javaOptions ++= Seq(
  // JVM memory tuning
  "-J-Xmx1024m",
  "-J-Xms512m",

  // Since play uses separate pidfile we have to provide it with a proper path
  // name of the pid file must be play.pid
  // s"-Dpidfile.path=/var/run/${packageName.value}/play.pid",

  // alternative, you can remove the PID file
  s"-Dpidfile.path=/dev/null",

  // Use separate configuration file for production environment
  s"-Dconfig.file=/opt/docker/conf/production.conf",

  // Use separate logger configuration file for production environment
  s"-Dlogger.file=/opt/docker/conf/production-logback.xml",

  // reference a logback config file that has no file appenders
  //s"-Dlogback.configurationFile=production-logback.xml"

  // You may also want to include this setting if you use play evolutions
  //"-DapplyEvolutions.default=true"
)