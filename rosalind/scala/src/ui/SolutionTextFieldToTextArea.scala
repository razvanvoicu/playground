package ui

import javafx.scene.control.{TextArea, TextField}
import javafx.scene.layout.Pane

trait SolutionTextFieldToTextArea extends HasView with StringToTextLayout {
  def solution(inp:String) : String

  override def view(title:String): Pane = {
    val inputBox = new TextField()
    inputBox.setPrefColumnCount(80)
    val outBox = new TextArea()
    outBox.setPrefColumnCount(80)
    outBox.setEditable(false)

    createLayout(title, inputBox, outBox, solution)
  }
}
