package FinalProject;

public class BabyLinkList {
	private BabyLink first;
	private BabyLink last;
	private int n;

	public BabyLinkList() {
		first = null;
		last = null;
		n = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertLast(String word) {
		BabyLink newBabyLink = new BabyLink(word);
		if (isEmpty()) {
			first = newBabyLink;
		} else {
			last.next = newBabyLink;
		}
		last = newBabyLink;
		n++;
	}

	public String getRandomWord() {
		int num = (int) (Math.random() * n);
		BabyLink current = first;
		for (int i = 0; i < num; i++) {
			current = current.next;
		}
		return current.word;
	}

}
