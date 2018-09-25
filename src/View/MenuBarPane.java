package View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
public class MenuBarPane {
	private MenuBar menuBar;
	
	private Menu fileMenu;
	private MenuItem newMenuItem;
	private MenuItem openMenuItem;
	private MenuItem closeMenuItem;
	private MenuItem saveMenuItem;
	private MenuItem exitMenuItem;
	
	private Menu viewMenu;
	private MenuItem wordCountMenuItem;
	private MenuItem sentenceCountMenuItem;
	private MenuItem fleschScoreMenuItem;
	private MenuItem oneVsThreeLoopMenuItem;
	
	private Menu editMenu;
	private MenuItem copyMenuItem;
	private MenuItem cutmenuItem;
	private MenuItem deleteMenuItem;
	private MenuItem pasteMenuItem;
	private MenuItem markovMenuItem;
	private MenuItem spellCheckMenuItem;
	
	public MenuBarPane() {
		menuBar = new MenuBar();
		buildFileMenu();
		buildViewMenu();
		buildEditMenu();
		menuBar.getMenus().addAll(fileMenu, viewMenu, editMenu);

	}
	
	public void buildFileMenu(){
		fileMenu = new Menu("File");
		
		newMenuItem = new MenuItem("New");
		openMenuItem = new MenuItem("Open");
		closeMenuItem = new MenuItem("Close");
		saveMenuItem = new MenuItem("Save");
		exitMenuItem = new MenuItem("exit");
		
		fileMenu.getItems().addAll(newMenuItem, openMenuItem, closeMenuItem, saveMenuItem, exitMenuItem);
		
	}
	
	
	public void buildViewMenu() {
		viewMenu = new Menu("View");
		
		wordCountMenuItem = new MenuItem("Word Count");
		sentenceCountMenuItem = new MenuItem("Sentence Count");
		fleschScoreMenuItem = new MenuItem("Flesch Score");
		oneVsThreeLoopMenuItem = new MenuItem("One vs Three Loop");
		spellCheckMenuItem = new MenuItem("Spell Check");
		
		viewMenu.getItems().addAll(wordCountMenuItem, sentenceCountMenuItem, fleschScoreMenuItem,
				oneVsThreeLoopMenuItem, spellCheckMenuItem);
	}
	
	public void buildEditMenu(){
		editMenu = new Menu("Edit");
		
		copyMenuItem = new MenuItem("Copy");
		cutmenuItem = new MenuItem("Cut");
		deleteMenuItem = new MenuItem("Delete");
		pasteMenuItem = new MenuItem("Paste");
		markovMenuItem = new MenuItem("Markov");
		
		editMenu.getItems().addAll(copyMenuItem, cutmenuItem, deleteMenuItem, pasteMenuItem, markovMenuItem);
	
	}
	
	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public Menu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(Menu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public MenuItem getNewMenuItem() {
		return newMenuItem;
	}

	public void setNewMenuItem(MenuItem newMenuItem) {
		this.newMenuItem = newMenuItem;
	}

	public MenuItem getOpenMenuItem() {
		return openMenuItem;
	}

	public void setOpenMenuItem(MenuItem openMenuItem) {
		this.openMenuItem = openMenuItem;
	}

	public MenuItem getCloseMenuItem() {
		return closeMenuItem;
	}

	public void setCloseMenuItem(MenuItem closeMenuItem) {
		this.closeMenuItem = closeMenuItem;
	}

	public MenuItem getSaveMenuItem() {
		return saveMenuItem;
	}

	public void setSaveMenuItem(MenuItem saveMenuItem) {
		this.saveMenuItem = saveMenuItem;
	}

	public MenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	public void setExitMenuItem(MenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}

	public Menu getViewMenu() {
		return viewMenu;
	}

	public void setViewMenu(Menu viewMenu) {
		this.viewMenu = viewMenu;
	}

	public MenuItem getWordCountMenuItem() {
		return wordCountMenuItem;
	}

	public void setWordCountMenuItem(MenuItem wordCountMenuItem) {
		this.wordCountMenuItem = wordCountMenuItem;
	}

	public MenuItem getSentenceCountMenuItem() {
		return sentenceCountMenuItem;
	}

	public void setSentenceCountMenuItem(MenuItem sentenceCountMenuItem) {
		this.sentenceCountMenuItem = sentenceCountMenuItem;
	}

	public MenuItem getFleschScoreMenuItem() {
		return fleschScoreMenuItem;
	}

	public void setFleschScoreMenuItem(MenuItem fleschScoreMenuItem) {
		this.fleschScoreMenuItem = fleschScoreMenuItem;
	}

	public MenuItem getOneVsThreeLoopMenuItem() {
		return oneVsThreeLoopMenuItem;
	}

	public void setOneVsThreeLoopMenuItem(MenuItem oneVsThreeLoopMenuItem) {
		this.oneVsThreeLoopMenuItem = oneVsThreeLoopMenuItem;
	}


	public Menu getEditMenu() {
		return editMenu;
	}

	public void setEditMenu(Menu editMenu) {
		this.editMenu = editMenu;
	}

	public MenuItem getCopyMenuItem() {
		return copyMenuItem;
	}

	public void setCopyMenuItem(MenuItem copyMenuItem) {
		this.copyMenuItem = copyMenuItem;
	}

	public MenuItem getCutmenuItem() {
		return cutmenuItem;
	}

	public void setCutmenuItem(MenuItem cutmenuItem) {
		this.cutmenuItem = cutmenuItem;
	}

	public MenuItem getDeleteMenuItem() {
		return deleteMenuItem;
	}

	public void setDeleteMenuItem(MenuItem deleteMenuItem) {
		this.deleteMenuItem = deleteMenuItem;
	}

	public MenuItem getPasteMenuItem() {
		return pasteMenuItem;
	}

	public void setPasteMenuItem(MenuItem pasteMenuItem) {
		this.pasteMenuItem = pasteMenuItem;
	}

	public MenuItem getMarkovMenuItem() {
		return markovMenuItem;
	}

	public void setMarkovMenuItem(MenuItem markovMenuItem) {
		this.markovMenuItem = markovMenuItem;
	}



	public MenuItem getSpellCheckMenuItem() {
		return spellCheckMenuItem;
	}

	public void setSpellCheckMenuItem(MenuItem spellCheckMenuItem) {
		this.spellCheckMenuItem = spellCheckMenuItem;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	
	

}
