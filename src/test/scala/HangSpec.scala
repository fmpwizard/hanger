import code.MyServer
import org.openqa.selenium.firefox.{FirefoxProfile, FirefoxDriver}
import org.specs2.mutable.Specification

class HangSpec extends Specification {
  "this test completes, but jvm never shutsdown" in {
    val server = new MyServer().start()
    val webDriver = new FirefoxDriver(new FirefoxProfile())
    webDriver.navigate.to("http://localhost:8080/")
    Thread.sleep(1000)
    webDriver.close()
    server.stop()
    success
  }
}