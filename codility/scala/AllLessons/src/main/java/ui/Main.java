package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox leftPane = new VBox();

        TreeItem<String> timeComplexity = new TreeItem<>("Time Complexity");
        timeComplexity.setExpanded(true);

        TreeItem<String> item = new TreeItem<>("Tape Equilibrium");
        timeComplexity.getChildren().add(item);

        item = new TreeItem<>("Frog Jump");
        timeComplexity.getChildren().add(item);

        TreeView<String> tree = new TreeView<>(timeComplexity);

        leftPane.getChildren().add(tree);

        TreeItem<String> countingElements = new TreeItem<>("Counting Elements");
        countingElements.setExpanded(true);

        item = new TreeItem<>("Tape Equilibrium");
        countingElements.getChildren().add(item);

        item = new TreeItem<>("Frog Jump");
        countingElements.getChildren().add(item);

        tree = new TreeView<>(countingElements);

        leftPane.getChildren().add(tree);

        StackPane root = new StackPane();
        root.getChildren().add(leftPane);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
