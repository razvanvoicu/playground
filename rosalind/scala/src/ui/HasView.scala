package ui

import javafx.scene.layout.Pane

trait HasView {
  def view(title:String) : Pane
}