package lvfpug.contextbounds

import lvfpug.contextbounds.defaults._
import lvfpug.contextbounds.defaults.DefaultStreams._
import java.io.PrintStream

/*
  Look at the Default trait and helpers set up in Defaults.scala first!
*/

object ContextBoundExamples extends App {
  def log[T <: PrintStream : Default](thing: Any, output: Option[T] = None) =
    output.getOrDefault.println(thing)

  // equivalent to
  // def log[T <: PrintStream](thing: Any, output: Option[T] = None)(implicit ev: Default[T]) = /* body */
  // note the rewrite of : Default as an implicit argument.
  // Since context bounds work through implicits, the name of the argument
  // doesn't matter, and the name of the implicit doesn't matter either.
  //  Only types are examined by the compiler!

  log("Hello!")  // Since output defaults to None, we can leave it off. Uses the default then!
  log("To a file", Some(new PrintStream("logfile"))) // or explicitly provide an output


  // Since Default takes any T, we can use our own case classes
  def pingHost[T <: Host : Default](target: Option[Host] = None) = {
    val host = target.getOrDefault
    log(s"Pinging $host")
    host.ping
  }

  //pingHost()


  // And we can add a helper for things to read a bit more cleanly (IMO)
  def withDefaulted[T : Default, U](t: Option[T])(f: T => U): U = f(t.getOrDefault)

  def betterPingHost[T <: Host : Default](target: Option[Host] = None) =
    withDefaulted(target) { host =>
      log(s"Pinging $host")
      host.ping
    }

  //betterPingHost()
}
