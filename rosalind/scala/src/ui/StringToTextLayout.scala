package ui

import javafx.application.Platform
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.{Pos, Insets}
import javafx.scene.control.{Button, Label, TextInputControl}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.{HBox, VBox}

import scala.concurrent.Future
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global

trait StringToTextLayout {
  def createLayout(title:String, inputBox: TextInputControl, outBox: TextInputControl, solution: String => String) = {
    val v = new VBox(20)
    val padding = new Insets(10,10,10,10)
    v.setPadding(padding)
    val titleLabel = new Label(title)
    titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: darkcyan;")
    titleLabel.setAlignment(Pos.CENTER)
    titleLabel.setPrefWidth(950)
    v.getChildren.add(titleLabel)
    v.getChildren.add(new Label("Input:"))
    v.getChildren.add(inputBox)
    val submit = new Button("Solve")
    submit.setStyle("-fx-font-size: 16; -fx-text-fill: firebrick; -fx-font-weight: bold;")

    val output = new Label("Output:")
    submit.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent): Unit = {
        outBox.setText("")
        submit.setDisable(true)
        inputBox.setDisable(true)
        output.setText("Crunching...")
        val resultFuture = Future{
          solution(inputBox.getText)
        }
        resultFuture.onComplete{
          case Success(v) => Platform.runLater(new Runnable{
            override def run(): Unit = {
              outBox.setText(solution(inputBox.getText))
              submit.setDisable(false)
              inputBox.setDisable(false)
              output.setText("Output:")
            }
          })
          case _ => println("future failed")
        }
      }
    })
    inputBox.setOnKeyPressed(new EventHandler[KeyEvent]{
      override def handle(event: KeyEvent): Unit = () match {
        case _ if event.getCode == KeyCode.ENTER => submit.fire()
        case _ => outBox.setText("")
      }
    })
    val submitContainer = new HBox()
    submitContainer.setAlignment(Pos.CENTER_RIGHT)
    submitContainer.getChildren.add(submit)
    v.getChildren.add(submitContainer)
    v.getChildren.add(output)
    v.getChildren.add(outBox)
    v
  }
}
