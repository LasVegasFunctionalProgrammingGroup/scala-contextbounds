package lvfpug.contextbounds

import java.io.PrintStream

package object defaults {
  trait Default[T] {
    def value: T
  }

  // this adds in a .getOrDefault on Option so
  // any Option[T] with a Default[T] in scope can be defaulted if it's None
  implicit class DefaultEvaluator[T : Default](t: Option[T]) {
    def getOrDefault: T = t.getOrElse(implicitly[Default[T]].value)
  }

  // helper function to create a new Default from any value
  private def default[T](x: T) = new Default[T] {
    def value = x
  }

  object Default {
    implicit val defaultInt = new Default[Int] {
      def value = 0
    }

    // Since tese are both Default[String] the compiler will
    // give an error if Default._ is imported and a Default[String]
    // is needed, due to ambiguity on which to use.
    //
    // HOWEVER: If only one is imported, like `import Default.defaultName`
    // the compiler shouldn't throw an error; only one implicit Defaul[T]
    // is in scope
    implicit val defaultName = default("John Doe")
    implicit val defaultName2 = default("DefaultName2")

    implicit val defaultHost = default(Host("localhost", "127.0.0.1"))
  }

  object DefaultStreams {
    implicit val defaultOut: Default[PrintStream] = default(System.out)
  }
}
