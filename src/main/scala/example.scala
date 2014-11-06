package lvfpug.contextbounds

import lvfpug.contextbounds.defaults._
import lvfpug.contextbounds.defaults.DefaultStreams._
import java.io.PrintStream

object ContextBoundExamples extends App {
  def log[T <: PrintStream : Default](thing: Any, output: Option[T] = None) =
    output.getOrDefault.println(thing)

  // equivalent to
  // def log[T <: PrintStream](thing: Any, output: Option[T] = None)(implicit ev: Default[T]) = /* body */

  log("Hello!")
  log("To a file", Some(new PrintStream("logfile")))



  def pingHost[T <: Host : Default](target: Option[Host] = None) = {
    val host = target.getOrDefault
    log(s"Pinging $host")
    host.ping
  }

  //pingHost()



  def withDefaulted[T : Default, U](t: Option[T])(f: T => U): U = f(t.getOrDefault)

  def betterPingHost[T <: Host : Default](target: Option[Host] = None) =
    withDefaulted(target) { host =>
      log(s"Pinging $host")
      host.ping
    }

  //betterPingHost()
}
