package uiexample

class Element[T <: Element[T]] {
  var x_off = 0.0
  var y_off = 0.0

  def location = (x_off, y_off)
  def locateAt(position: Tuple2[Double, Double]): Element[T] = {
    x_off = position._1
    y_off = position._2
    this
  }
}

abstract class Button(val text: String, val width: Double, val height: Double) extends Element[Button] {
  def action
}

class DefaultButton(text: String, width: Double, height: Double) extends Button(text, width, height) {
  def action = println("Hello!")
}

class TextBox(var text: String, val width: Double, val height: Double) extends Element[TextBox] {
  def setText(newText: String) = {
    text = newText
    reflowText
  }

  def reflowText = println("Reflowing text.")
}
