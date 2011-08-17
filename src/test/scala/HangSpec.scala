import code.MyServer
import org.openqa.selenium.firefox.FirefoxDriver
import org.specs2.execute.Result
import org.specs2.mutable.Specification
import org.specs2.specification.Around

class HangSpec extends Specification {
  "this test completes, but jvm never shutsdown. But using an Around trait fixed it!" in new Webserver {
    val webDriver = new FirefoxDriver()
    webDriver.navigate.to("http://localhost:8080/")
    Thread.sleep(1000)
    webDriver.close()
    success
  }
}


trait Webserver extends Around {
  lazy val server = new MyServer()
  def around[T <% Result](t: =>T) = {
    server.start()
    try {
      t
    } finally {
      server.stop()
    }
  }
}