package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class TextAreaPane {
	HBox textAreaLine;
	private static TextArea textArea;
	
	public TextAreaPane() {
		textArea = new TextArea();
		textArea.setMaxSize(650, 630);
		textArea.setWrapText(true);
		
	}

	public static TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public HBox getTextAreaLine() {
		return textAreaLine;
	}

	public void setTextAreaLine(HBox textAreaLine) {
		this.textAreaLine = textAreaLine;
	}

	
	
	

}
