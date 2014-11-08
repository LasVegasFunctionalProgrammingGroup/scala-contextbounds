scala-contextbounds
===================

Usage of context bounds in Scala. (That's the `: B` part in `foo[A : B] = /* body */)

The first example is the code in `/src/main/scala`, read `Host.scala` and `Defaults.scala` first, then `example.scala`!

This example shows off adding a `Default` trait, which is then used to provide a default value for any `Option[T]` that is a `None`.
ex:
``` scala
  val a: Option[Int] = None
  println(a.getOrDefault) // prints 0
```

The second example is in the sbt subproject `uiexample`, in the directory also named `uiexample`.

This example shows how context bounds can be used to add behavior without touching the structures they add logic for!
