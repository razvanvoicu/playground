package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static VBox solutionPane = new VBox();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rosalind Challenges");
        TreeItem<Control> rootItem = UIMenu.menu();
        rootItem.setExpanded(true);
        TreeView<Control> tree = new TreeView<> (rootItem);
        tree.setPrefWidth(450);

        HBox root = new HBox();
        root.getChildren().add(tree);
        root.getChildren().add(solutionPane);
        primaryStage.setScene(new Scene(root, 1400, 1024));
        primaryStage.show();
    }
}
