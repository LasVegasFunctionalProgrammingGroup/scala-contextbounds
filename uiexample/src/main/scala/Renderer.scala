package object uiexample {
  trait Renderer[T <: Element[T]] {
    def draw(elem: T): Unit
  }

  implicit object ButtonRenderer extends Renderer[Button] {
    def draw(elem: Button): Unit =
      println(s"Drawing ${elem.getClass} at ${elem.location}")
  }

  implicit object TextBoxRenderer extends Renderer[TextBox] {
    def draw(elem: TextBox): Unit =
      println(s"Drawing ${elem.getClass} at ${elem.location} with text ${elem.text}")
  }

  implicit class DrawSugar[T <: Element[T] : Renderer](elem: T) {
    def draw = implicitly[Renderer[T]].draw(elem)
  }
}
