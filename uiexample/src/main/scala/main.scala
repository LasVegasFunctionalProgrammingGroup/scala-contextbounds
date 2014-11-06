package uiexample

object main extends App {
  val button = new Button("someButton", 10, 12) {
    def action = println(s"Button '${this.text}' was clicked")
  }
  val textbox = new TextBox("hello!", -4, 5)
  textbox.setText("Hello, this string is longer!")

  button.draw
  textbox.draw
}
