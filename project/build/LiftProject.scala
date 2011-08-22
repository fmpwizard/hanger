import sbt._

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) {
  val liftVersion = "2.4-M3"
  override def testClasspath  = super.testClasspath +++ ("src" / "main" / "webapp")

  def specs2Framework = new TestFramework("org.specs2.runner.SpecsFramework")
  override def testFrameworks = super.testFrameworks ++ Seq(specs2Framework)

  override def includeTest(s: String) = s.endsWith("Spec")

  // uncomment the following if you want to use the snapshot repo
  val scalatoolsSnapshot = ScalaToolsSnapshots
  val sonatype_snapshot = "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"

  // If you're using JRebel for Lift development, uncomment
  // this line
  // override def scanDirectories = Nil

  override def libraryDependencies = Set(
    "junit" % "junit" % "4.5" % "test->default",
    "org.seleniumhq.selenium" % "selenium" % "2.0rc2" % "test" ,
    "org.seleniumhq.selenium" % "selenium-server" % "2.0rc2" % "test" withSources (),
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "test",
    "org.specs2" %% "specs2" % "1.4" % "test",
    "org.specs2" %% "specs2-scalaz-core" % "5.1-SNAPSHOT" % "test",
    "org.scalatest" % "scalatest" % "1.3" % "test->default",
    "ch.qos.logback" % "logback-classic" % "0.9.26"
  ) ++ super.libraryDependencies
}
