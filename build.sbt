sonatypeSettings

releaseSettings
ReleaseKeys.publishArtifactsAction := PgpKeys.publishSigned.value

name := "scoped-config"
organization := "com.gilt"

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-feature",
  "-unchecked"
)

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

homepage := Some(url("https://github.com/gilt/scoped-config"))

licenses := Seq("MIT" -> url("https://raw.githubusercontent.com/gilt/scoped-config/master/LICENSE"))

publishTo := Some {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    "snapshots" at nexus + "content/repositories/snapshots"
  else
    "releases" at nexus + "service/local/staging/deploy/maven2"
}

pomExtra := (
  <scm>
    <url>https://github.com/gilt/scoped-config.git</url>
    <connection>scm:git:git@github.com:gilt/scoped-config.git</connection>
  </scm>
  <developers>
    <developer>
      <id>myyk</id>
      <name>Myyk Seok</name>
      <url>https://github.com/myyk</url>
    </developer>
  </developers>
)
