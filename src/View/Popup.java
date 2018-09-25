package View;

import java.io.FileNotFoundException;

import FinalProject.MasterLink;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
	MasterLink list;

	public static void display() {
		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Markov");

		Label keyWordLbl = new Label("Enter keyword:");
		Label lengthLbl = new Label("Enter Length:  ");

		TextField keyWordField = new TextField();
		keyWordField.setPromptText("key word");
		keyWordField.setPrefWidth(90);

		TextField lengthField = new TextField();
		lengthField.setPromptText("Length");
		lengthField.setPrefWidth(90);

		Button generateBtn = new Button("Generate");

		HBox firstLine = new HBox(20);
		firstLine.getChildren().addAll(keyWordLbl, keyWordField);
		firstLine.setPadding(new Insets(20));

		HBox secondLine = new HBox(20);
		secondLine.setPadding(new Insets(20));
		secondLine.getChildren().addAll(lengthLbl, lengthField);

		HBox button = new HBox();
		button.setPadding(new Insets(20));
		button.getChildren().add(generateBtn);
		button.setAlignment(Pos.CENTER);

		VBox layout = new VBox(10);
		layout.getChildren().addAll(firstLine, secondLine, button);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 300, 250);
		popupwindow.setScene(scene1);
		popupwindow.show();

		generateBtn.setOnAction(e -> {
			try {
				RootPane.storeLinkedList(RootPane.getList(), "inputData/WarAndPeace.txt");
				reading(lengthField, keyWordField);
				popupwindow.close();

			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		});

	}

	public static String generate(String word, int num) {
		String s = " ";
		s += TextAreaPane.getTextArea().getText() + " ";
		String s2 = word;
		for (int i = 0; i < num; i++) {
			s2 = RootPane.getList().findWords(s2).getRandomWord();
			s += s2 + " ";

		}
		return s;
	}

	public static boolean reading(TextField num, TextField word) throws FileNotFoundException {
		try {
			int nums = Integer.parseInt(num.getText());
			String words = (word.getText());
			TextAreaPane.getTextArea().setText(generate(words, nums));
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;

	}

	public MasterLink getList() {
		return list;
	}

	public void setList(MasterLink list) {
		this.list = list;
	}
	
}