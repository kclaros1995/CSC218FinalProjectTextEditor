package View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import FinalProject.HashTable;
import FinalProject.MasterLinkList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RootPane {
	private static MasterLinkList list = new MasterLinkList();
	public int syllabus;
	public double fleshS;
	public int size;
	public int counter;
	private HBox textAreaBox;
	private BorderPane rootPane;
	private MenuBarPane menuBarPane;
	private MenuBar menuBar;
	private TextAreaPane textAreaPane;
	private TextArea textArea;
	private StatusBarPane statusBarPane;
	private Label statusBar;
	private String words;
	private HashTable hashDict;

	public RootPane(Main main) {

		rootPane = new BorderPane();

		menuBarPane = new MenuBarPane();
		menuBar = menuBarPane.getMenuBar();
		rootPane.setTop(menuBar);

		textAreaPane = new TextAreaPane();
		textArea = TextAreaPane.getTextArea();
		rootPane.setCenter(textArea);

		statusBar = new Label();
		String words = String.format(								 // 20.2f means it has two digits to the left
				"Words: %-20dSentences: %-20dFlesch Score: " + (Double.isFinite(fleshS) ? "%-20.2f" : "%-20s"), size,
				counter, (Double.isFinite(fleshS) ? fleshS : " "));
		// %-20d means the it will have a width of 20, d specifies a decimal				//$-20s - s if for indices 
		textArea.textProperty().addListener(e -> {
			calculateFleschScore();		// calculate flesh score has the other methods in it to save time 
			statusBar.setText(String.format(
					"Words: %-20dSentences: %-20dFlesch Score: " + (Double.isFinite(fleshS) ? "%-20.2f" : "%-20s"),
					size, counter, (Double.isFinite(fleshS) ? fleshS : "N/A")));

		});
		statusBar.setText(words);
		rootPane.setBottom(statusBar);

		spellCheck();
		buildMenuBarEventHandlers();
		makeDictionary();

	}

	public void buildMenuBarEventHandlers() {
		buildFileMenuEventHandlers();
		buildViewMenuEventHandlers();
		buildEditMenuEventHandlers();

	}

	public void buildFileMenuEventHandlers() {
		FileChooser fileChooser = new FileChooser();

		menuBarPane.getNewMenuItem().setOnAction(e -> {
			textAreaPane.getTextArea().clear();
		});

		menuBarPane.getOpenMenuItem().setOnAction(e -> {
			File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
			Scanner input = null;
			if (file != null) {
				try {
					StringBuilder sb = new StringBuilder();	// places the string of the story into s
					input = new Scanner(file);				// string builder
					input.useDelimiter("\\z");		// searches for the end of the input
					while (input.hasNext()) {
						sb.append(input.next());
					}
					String text2 = sb.toString();	// the regular string text2 will have the value of the 
					textArea.setText(text2);			// string builder one

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				input.close();
			}
		});

		menuBarPane.getCloseMenuItem().setOnAction(ex -> {
			Stage stage = new Stage();
			Button yesButton = new Button("Yes");
			Button noButton = new Button("No");
			Label label = new Label("Do You want to save?");
			HBox labelBox = new HBox();
			HBox buttonBox = new HBox(20);
			VBox alignment = new VBox();
			labelBox.getChildren().add(label);
			labelBox.setPadding(new Insets(20, 0, 20, 0));
			labelBox.setAlignment(Pos.CENTER);
			buttonBox.getChildren().addAll(yesButton, noButton);
			yesButton.setAlignment(Pos.CENTER_LEFT);
			noButton.setAlignment(Pos.CENTER_RIGHT);
			alignment.getChildren().addAll(labelBox, buttonBox);
			buttonBox.setAlignment(Pos.CENTER);
			buttonBox.setPadding(new Insets(0, 30, 0, 30));
			stage.setTitle("Close");
			Scene scene = new Scene(alignment, 200, 150);
			stage.setScene(scene);
			stage.show();
			yesButton.setOnAction(e-> {
				FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(exFilter);
				File file = fileChooser.showSaveDialog(Main.getPrimaryStage());
				if (file != null) {
					saveFile(textArea.getText(), file);		// calling the saveFile Method
				}
				textArea.clear();
				Main.getPrimaryStage().close();
				stage.close();
			});
			noButton.setOnAction(e->{
				Main.getPrimaryStage().close();
				stage.close();
			});

		});

		menuBarPane.getSaveMenuItem().setOnAction(e -> {
			FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(exFilter);
			File file = fileChooser.showSaveDialog(Main.getPrimaryStage());
			if (file != null) {
				saveFile(textArea.getText(), file);		// same as close 
			}
		});
		menuBarPane.getExitMenuItem().setOnAction(e -> {
			Stage stage = new Stage();
			HBox labelBox = new HBox();
			Label label = new Label("Are you sure you want to exit?");
			labelBox.getChildren().add(label);
			labelBox.setAlignment(Pos.CENTER);
			Button yesButton = new Button("Yes");
			Button noButton = new Button("No");
			HBox buttonBox = new HBox(20);
			buttonBox.getChildren().addAll(yesButton, noButton);
			buttonBox.setPadding(new Insets(20));
			buttonBox.setAlignment(Pos.CENTER);
			VBox alignment = new VBox();
			alignment.getChildren().addAll(labelBox, buttonBox);
			Scene scene = new Scene(alignment, 220, 100);
			stage.setScene(scene);
			stage.setTitle("EXIT");
			stage.show();
			yesButton.setOnAction(ex->{
				Main.getPrimaryStage().close();
				stage.close();
			});
			
			noButton.setOnAction(ex->{
				stage.close();
			});
	

		});

	}

	public void buildViewMenuEventHandlers() {
		menuBarPane.getWordCountMenuItem().setOnAction(e -> {
			wordCount();
			syllabus = 0;
			counter = 0;
			fleshS = 0;
			statusBar.setText(String.format(		
					"Words: %-20dSentences: %-20dFlesch Score: " + (Double.isFinite(fleshS) ? "%-20.2f" : "%-20s"),
					size, counter, (Double.isFinite(fleshS) ? fleshS : " ")));

		});
		menuBarPane.getSentenceCountMenuItem().setOnAction(e -> {
			sentenceCount();
			syllabus = 0;
			size = 0;
			fleshS = 0;
			statusBar.setText(String.format(
					"Words: %-20dSentences: %-20dFlesch Score: " + (Double.isFinite(fleshS) ? "%-20.2f" : "%-20s"),
					size, counter, (Double.isFinite(fleshS) ? fleshS : "N/A")));

		});
		menuBarPane.getFleschScoreMenuItem().setOnAction(e -> {
			calculateFleschScore();
			syllabus = 0;
			size = 0;
			counter = 0;

			statusBar.setText(String.format(
					"Words: %-20dSentences: %-20dFlesch Score: " + (Double.isFinite(fleshS) ? "%-20.2f" : "%-20s"),
					size, counter, (Double.isFinite(fleshS) ? fleshS : "N/A")));
		});
		menuBarPane.getSpellCheckMenuItem().setOnAction(e -> {
			spellCheck();

		});
		menuBarPane.getOneVsThreeLoopMenuItem().setOnAction(e -> {
			String words100 = readFile(100);		
			long words100OneLoop = oneLoopCount(words100);
			long words100ThreeLoops = threeLoopCount(words100);
			String words1000 = readFile(1000);	
			long words1000OneLoop = oneLoopCount(words1000);
			long words1000ThreeLoops = threeLoopCount(words1000);
			String words10000 = readFile(10000);
			long words10000OneLoop = oneLoopCount(words10000);
			long words10000ThreeLoops = threeLoopCount(words10000);
			String words100000 = readFile(100000);
			long words100000OneLoop = oneLoopCount(words100000);	
			long words100000ThreeLoops = threeLoopCount(words100000);

			saveFile(
					String.format("100 words:\n" + "\tOne Loop: %d\n" + "\tThree Loops: %d\n" + "1000 words:\n"
							+ "\tOne Loop: %d\n" + "\tThree Loops: %d\n" + "10000 words:\n" + "\tOne Loop: %d\n"
							+ "\tThree Loops: %d\n" + "100000 words:\n" + "\tOne Loop: %d\n" + "\tThree Loops: %d",
							words100OneLoop, words100ThreeLoops, words1000OneLoop, words1000ThreeLoops,
							words10000OneLoop, words10000ThreeLoops, words100000OneLoop, words100000ThreeLoops),
					new File("outputData/result.txt"));

			final CategoryAxis xAxis = new CategoryAxis();
			final NumberAxis yAxis = new NumberAxis();
			xAxis.setLabel("Number of Words");
			yAxis.setLabel("Time(ns)");
			final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
			lineChart.setTitle("One vs Three Loop");

			XYChart.Series<String, Number> series1 = new XYChart.Series<>();
			series1.setName("One Loop");
			series1.getData().add(new XYChart.Data<String, Number>("100", words100OneLoop));
			series1.getData().add(new XYChart.Data<String, Number>("1000", words1000OneLoop));
			series1.getData().add(new XYChart.Data<String, Number>("10000", words10000OneLoop));
			series1.getData().add(new XYChart.Data<String, Number>("100000", words100000OneLoop));

			XYChart.Series<String, Number> series2 = new XYChart.Series<>();
			series2.setName("Three Loop");
			series2.getData().add(new XYChart.Data<String, Number>("100", words100ThreeLoops));
			series2.getData().add(new XYChart.Data<String, Number>("1000", words1000ThreeLoops));
			series2.getData().add(new XYChart.Data<String, Number>("10000", words10000ThreeLoops));
			series2.getData().add(new XYChart.Data<String, Number>("100000", words100000ThreeLoops));

			Stage stage = new Stage();
			Scene scene = new Scene(lineChart, 400, 400);
			lineChart.getData().add(series1);
			lineChart.getData().add(series2);
			stage.setScene(scene);
			stage.show();

		});
	}

	public void buildEditMenuEventHandlers() {
		menuBarPane.getCopyMenuItem().setOnAction(e -> {
			textAreaPane.getTextArea().copy();

		});
		menuBarPane.getCutmenuItem().setOnAction(e -> {
			textAreaPane.getTextArea().cut();
		});
		menuBarPane.getDeleteMenuItem().setOnAction(e -> {
			textAreaPane.getTextArea().deleteText(0, textAreaPane.getTextArea().getText().length());
		});
		menuBarPane.getPasteMenuItem().setOnAction(e -> {
			textAreaPane.getTextArea().paste();

		});
		menuBarPane.getMarkovMenuItem().setOnAction(e -> {
			Popup.display();
			
		});
	}

	public long oneLoopCount(String text) {
		long startTime = System.nanoTime();
		int wordCount = 0;
		int sentenceCount = 0;
		int syllableCount = 0;
		Pattern wordSentencePattern = Pattern.compile("([\\w'-]+)|([^.!?\\s][^.!?]*)");
		Pattern syllablePattern = Pattern.compile("([aiouy]\\b)|([aeiouy]+\\B)");
		Matcher wordSentenceMatcher = wordSentencePattern.matcher(text);
		Matcher syllableMatcher = syllablePattern.matcher("");
		while (wordSentenceMatcher.find()) {
			if (wordSentenceMatcher.group(1) != null) {
				wordCount++;
				syllableMatcher.reset(wordSentenceMatcher.group(1));
				while (syllableMatcher.find()) {
					syllableCount++;
				}
			} else {
				sentenceCount++;
			}
		}
		return System.nanoTime() - startTime;
	}

	public long threeLoopCount(String text) {
		long startTime = System.nanoTime();
		wordCount(text);
		sentenceCount(text);
		syllableCount(text);
		return System.nanoTime() - startTime;
	}

	public String readFile(int wordCount) {
		try {
			File f = new File("inputData/WarAndPeace.txt");
			Scanner input = new Scanner(new BufferedReader(new FileReader(f)));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < wordCount && input.hasNext(); i++) {
				sb.append(input.next());
				sb.append(" ");
			}
			input.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void saveFile(String content, File file) {
		try {
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openFile() {
		try {
			storeLinkedList(list, "inputData/WarAndPeace.txt");
		} catch (FileNotFoundException e) {
		}
	}

	public static void storeLinkedList(MasterLinkList list, String fileName) throws FileNotFoundException {
		File f = new File(fileName);
		Scanner input = new Scanner(new BufferedReader(new FileReader(f)));
		String word = " ";
		while (input.hasNext()) {
			word = input.next();
			list.insertWord(word);
		}
		input.close();
	}

	public int wordCount(String text) {
		String patt = "[\\w'-]+";
		Pattern pattern = Pattern.compile(patt);
		size = 0;
		Matcher match = pattern.matcher(text);
		while (match.find()) {
			size++;
		}
		return size;
	}

	public int sentenceCount(String text) {
		String patt = "([^.!?\\s][^.!?]*)";
		Pattern pattern = Pattern.compile(patt);
		counter = 0;
		Matcher match = pattern.matcher(text);
		while (match.find()) {
			counter++;
		}
		return counter;
	}

	public int syllableCount(String text) {
		String patt = "([aiouy]\\b)|([aeiouy]+\\B)";
		Pattern pattern = Pattern.compile(patt);
		int sylllables = 0;
		Matcher match = pattern.matcher(text);
		while (match.find()) {
			sylllables++;
			match.group();
		}
		return sylllables;
	}

	public int wordCount() {
		String patt = "[\\w'-]+";
		Pattern pattern = Pattern.compile(patt);
		size = 0;
		Matcher match = pattern.matcher(textArea.getText());
		while (match.find()) {
			size++;
		}
		return size;
	}

	public int sentenceCount() {
		String patt = "([^.!?\\s][^.!?]*)";
		Pattern pattern = Pattern.compile(patt);
		counter = 0;
		Matcher match = pattern.matcher(textArea.getText());
		while (match.find()) {
			counter++;
		}
		return counter;
	}

	public int syllableCount() {
		String patt = "([aiouy]\\b)|([aeiouy]+\\B)";
		Pattern pattern = Pattern.compile(patt);
		int sylllables = 0;
		Matcher match = pattern.matcher(textArea.getText());
		while (match.find()) {
			sylllables++;
			match.group();
		}
		return sylllables;
	}

	public double calculateFleschScore() {
		if (textArea.getText().length() > 0) {
			int words = wordCount();
			fleshS = 206.835 - 1.015 * ((double) words / sentenceCount()) - 84.6 * ((double) syllableCount() / words);
		} else if (textArea.getText().length() <= 0) {
			fleshS = 0.0;
		}
		return fleshS;

	}

	public void saveDictionary() {
		try {
			FileOutputStream fos = new FileOutputStream("inputData/dictionary.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(hashDict);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadDictionary() {
		try {
			FileInputStream fis = new FileInputStream("inputData/dictionary.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			hashDict = (HashTable) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void makeDictionary() {
		try {
			FileReader fr = new FileReader("inputData/dictionary.txt");
			BufferedReader br = new BufferedReader(fr);
			Scanner scanner = new Scanner(br);
			hashDict = new HashTable(100000);
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine().toLowerCase();
				hashDict.insert(word);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void spellCheck() {
		StringReader spelling = new StringReader(textArea.getText());
		Scanner scanner = new Scanner(spelling);
		scanner.useDelimiter("[\\W'-]+");
		int startPos = 0;
		while (scanner.hasNext()) {
			String word = scanner.next();
			System.out.println(word);
			if (hashDict.contains(word.toLowerCase()) == false) {
				textArea.selectRange(startPos = textArea.getText().indexOf(word), startPos + word.length());
				break;
			} else {
			}
		}
		scanner.close();
	}

	public BorderPane getRootPane() {
		return rootPane;
	}

	public void setRootPane(BorderPane rootPane) {
		this.rootPane = rootPane;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public MenuBarPane getMenuBarPane() {
		return menuBarPane;
	}

	public void setMenuBarPane(MenuBarPane menuBarPane) {
		this.menuBarPane = menuBarPane;
	}

	public TextAreaPane getTextAreaPane() {
		return textAreaPane;
	}

	public void setTextAreaPane(TextAreaPane textAreaPane) {
		this.textAreaPane = textAreaPane;
	}

	public static MasterLinkList getList() {
		return list;
	}

	public static void setList(MasterLinkList list) {
		RootPane.list = list;
	}

	public int getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(int syllabus) {
		this.syllabus = syllabus;
	}

	public double getFleshS() {
		return fleshS;
	}

	public void setFleshS(double fleshS) {
		this.fleshS = fleshS;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public HBox getTextAreaBox() {
		return textAreaBox;
	}

	public void setTextAreaBox(HBox textAreaBox) {
		this.textAreaBox = textAreaBox;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public StatusBarPane getStatusBarPane() {
		return statusBarPane;
	}

	public void setStatusBarPane(StatusBarPane statusBarPane) {
		this.statusBarPane = statusBarPane;
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

	public HashTable getHashDict() {
		return hashDict;
	}

	public void setHashDict(HashTable hashDict) {
		this.hashDict = hashDict;
	}

}
