package FinalProject;

public class MasterLink {
	public String keyword;
	public BabyLinkList babyLinkList;
	public MasterLink next;

	public MasterLink(String newWord) {
		keyword = newWord;
		next = null;
	}

	public void createBabyLink() {
		babyLinkList = new BabyLinkList();
	}

	public void insertToBabyLinkList(String word) {
		babyLinkList.insertLast(word);
	}

	public void displaySuperLink() { 
		System.out.println("Keyword->" + keyword + " ");
	}

}
