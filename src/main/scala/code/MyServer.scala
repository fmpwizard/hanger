package code

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

class MyServer {
  val server = new Server(8080)
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
    server.join()
  }
}

object MyServer {
  def main(args: Array[String]) {
    new MyServer().start()
  }
}