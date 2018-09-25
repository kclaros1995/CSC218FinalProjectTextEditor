package View;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class StatusBarPane {
	private Label statusBar;
	private String words;
	TextArea textArea;
	RootPane rootPane;
	
	
	public StatusBarPane() {
		statusBar = new Label();
		statusBar.setText(words);
	}
	


	public Label getStatusBar() {
		return statusBar;
	}


	public void setStatusBar(Label statusBar) {
		this.statusBar = statusBar;
	}



	public String getWords() {
		return words;
	}


	public void setWords(String words) {
		this.words = words;
	}


	
	

}
