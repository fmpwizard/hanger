package code

import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxProfile}

object HangTest {
  def main(args: Array[String]) {
    val server = new MyServer().start() 
    val webDriver = new FirefoxDriver(new FirefoxProfile)
    webDriver.navigate.to("http://localhost:8080/")
    Thread.sleep(1000)
    webDriver.close()
    server.stop
  }
}