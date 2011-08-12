package code.comet

import net.liftweb.http._

class HangingComet extends CometActor {
  def render = {
    <h1>Hello</h1>
  }
}