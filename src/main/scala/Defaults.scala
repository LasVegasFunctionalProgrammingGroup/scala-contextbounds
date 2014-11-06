package lvfpug.contextbounds

import java.io.PrintStream

package object defaults {
  trait Default[T] {
    def value: T
  }

  implicit class DefaultEvaluator[T : Default](t: Option[T]) {
    def getOrDefault: T = t.getOrElse(implicitly[Default[T]].value)
  }

  private def default[T](x: T) = new Default[T] {
    def value = x
  }

  object Default {
    implicit val defaultInt = new Default[Int] {
      def value = 0
    }

    implicit val defaultName = default("John Doe")

    implicit val defaultHost = default(Host("localhost", "127.0.0.1"))
  }

  object DefaultStreams {
    implicit val defaultOut: Default[PrintStream] = default(System.out)
  }
}
