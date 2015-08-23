package ui

import javafx.scene.control._
import javafx.scene.layout.Pane

trait SolutionTextAreaToTextField extends HasView with StringToTextLayout {
  def solution(inp:String) : String

  override def view(title:String): Pane = {
    val inputBox = new TextArea()
    inputBox.setPrefColumnCount(80)
    inputBox.setPrefRowCount(30)
    inputBox.setWrapText(true)
    val outBox = new TextField()
    outBox.setPrefColumnCount(80)
    outBox.setEditable(false)

    createLayout(title, inputBox, outBox, solution)
  }
}
