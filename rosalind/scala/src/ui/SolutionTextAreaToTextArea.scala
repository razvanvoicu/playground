package ui

import javafx.scene.control.TextArea
import javafx.scene.layout.Pane

trait SolutionTextAreaToTextArea extends HasView with StringToTextLayout {
  def solution(inp:String) : String

  override def view(title:String): Pane = {
    val inputBox = new TextArea()
    inputBox.setPrefColumnCount(80)
    inputBox.setPrefRowCount(30)
    inputBox.setWrapText(true)
    val outBox = new TextArea()
    outBox.setPrefColumnCount(80)
    outBox.setPrefRowCount(30)
    outBox.setWrapText(true)
    outBox.setEditable(false)

    createLayout(title, inputBox, outBox, solution)
  }
}
