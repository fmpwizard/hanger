package code.snippet

import org.specs2._
import specification._
import matcher._

import net.liftweb.common.Logger



trait SeleniumSpec extends Specification with BeforeExample with Logger {
  import org.mortbay.jetty.{ Connector, Server}
  import org.mortbay.jetty.webapp.{ WebAppContext }
  import org.openqa.selenium.server.RemoteControlConfiguration
  import org.openqa.selenium.server.SeleniumServer
  import com.thoughtworks.selenium._

  /** the map method allows to "post-process" the fragments after their creation */
  override def map(fs: =>Fragments) = Step(startSelenium) ^ fs ^ Step(stopSelenium)

  lazy val GUI_PORT             = 8090
  lazy val SELENIUM_SERVER_PORT = 4444
  lazy val rc = new RemoteControlConfiguration()
  rc.setPort(SELENIUM_SERVER_PORT)
  lazy val seleniumserver       = new SeleniumServer(rc)
  lazy val server               = new Server(GUI_PORT)
  lazy val selenium             = new DefaultSelenium(
    "localhost", SELENIUM_SERVER_PORT, "*firefox", "http://localhost:"+GUI_PORT+"/"
  )

  // Setting up the Selenium Server for the duration of the tests


  def startSelenium {
    /*  This code takes care of the following:

        1. Start an instance of your web application
        2. Start an instance of the Selenium backend
        3. Start an instance of the Selenium client
        4. Reset the Categories table
    */

    // Setting up the jetty instance which will be running the
    // GUI for the duration of the tests

    lazy val context = new WebAppContext()
    context.setServer(server)
    context.setContextPath("/")
    context.setWar("src/main/webapp")
    server.addHandler(context)
    server.start()

    seleniumserver.boot()
    seleniumserver.start()
    seleniumserver.getPort()

    // Setting up the Selenium Client for the duration of the tests

    selenium.start()
  }

  def stopSelenium {
    // Close everyhing when done
    selenium.close()
    server.stop()
    seleniumserver.stop()
  }
}


object SeleniumSuiteSpec extends Specification with Logger { def is =
  sequential ^ stopOnFail                           ^
  "These are the selenium specifications"           ^
  include(InventoryCategoriesCreateTestSpecs)

}


object InventoryCategoriesCreateTestSpecs extends SeleniumSpec with DataTables { def is =

  "This is a specification to create a category 'InventoryCategoriesCreate' "   ^
                                                                                p^
  "The 'InventoryCategoriesCreate' class should create a Category"              ^
    args(sequential=true)                                                       ^
    "Create Display, Storage and Power"                                         ! e1^
                                                                                end

  def before = getToPage
  /**
   * Actual test code
   */
  def e1 ={
    createVerCat
  }





  /**
   * Boiler plate code
   */
  def getToPage = {
    selenium.open("/")
  }

  /**
   * Create category and verify it
   */
  def createVerCat = {
    Thread.sleep(2000)
    selenium.isTextPresent("Hello") must_== true
  }

}

