import code.MyServer
import org.openqa.selenium.firefox.FirefoxDriver
import org.specs2.execute.Result
import org.specs2.mutable.Specification
import org.specs2.specification.{Around, BeforeAfterExample, Scope}

class HangSpec extends Specification {
  val server = new MyServer

  "this test completes, but jvm never shutsdown" in {
    server.start()
    val webDriver = new FirefoxDriver()
    webDriver.navigate.to("http://localhost:8080/")
    Thread.sleep(1000)
    webDriver.close()
    server.stop()
    success
  }
}
