package code

import org.eclipse.jetty.server.{DispatcherType, Server}
import org.eclipse.jetty.servlet.{ServletHolder, FilterHolder}
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.util.resource.Resource;
import net.liftweb.http.LiftFilter
import java.util.EnumSet

class MyServer {
  val server = new Server()
  val channelConnector = new SelectChannelConnector()
  channelConnector.setPort(8080)
  server.setConnectors(Array(channelConnector))
  val context = new WebAppContext()
  context.setServer(server) 
  context.setContextPath("/")
  context.setWar("src/main/webapp")
  server.setHandler(context)

  def start() = {
    server.start()
    this
  }

  def stop() {
    server.stop()
  }
}