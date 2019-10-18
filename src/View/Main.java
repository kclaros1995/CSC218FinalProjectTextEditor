
package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	private static Stage primaryStage;
	private Pane rootPane;
	public static void main(String[]args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			buildRootPane();
			this.primaryStage = primaryStage;
			Scene scene = new Scene(rootPane, 725, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Text Editor");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static  Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Pane getRootPane() {
		return rootPane;
	}

	public void setRootPane(Pane rootPane) {
		this.rootPane = rootPane;
	}

	public void buildRootPane() {
		this.rootPane = new RootPane(this).getRootPane();
	}

	

}
