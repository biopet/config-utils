organization := "com.github.biopet"
name := "biopet-config-utils"

scalaVersion := "2.11.11"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "com.github.biopet" %% "biopet-common-utils" % "0.1-SNAPSHOT" changing()
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.3"
libraryDependencies += "com.gilt" %% "jerkson" % "0.6.9"
libraryDependencies += "org.yaml" % "snakeyaml" % "1.17"
libraryDependencies += "org.scalaj" % "scalaj-collection_2.11" % "1.6"

libraryDependencies += "com.github.biopet" %% "biopet-test-utils" % "0.1-SNAPSHOT" % Test changing()

useGpg := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommand("publishSigned"),
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges
)
