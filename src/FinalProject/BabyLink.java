package FinalProject;

public class BabyLink {
	public String word;
	public BabyLink next;

	public BabyLink(String word) {
		this.word = word;
	}

	public void displayBabyLink() {
		System.out.println(word + " ");
	}
}
