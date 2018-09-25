package FinalProject;

import java.util.HashMap;

public class MasterLinkList {
	private MasterLink first;
	private MasterLink last;
	private MasterLink current;
	private HashMap<String,BabyLinkList> wordMap = new HashMap<>();
	String prev = null;
	private int n;

	public MasterLinkList() {
		first = null;
		last = null;
		n = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}
	
	public void insertWord(String keyword) {
		if(!wordMap.containsKey(keyword)) {
			wordMap.put(keyword, new BabyLinkList());
		}
		if(prev != null) {
			wordMap.get(prev).insertLast(keyword);
		}
		prev = keyword;
	}
	
	public BabyLinkList findWords(String keyword) {
		return wordMap.get(keyword);
	}
	public MasterLink find(String keyword) {
		MasterLink theLink = first;
		if (!isEmpty()) {
			while (!theLink.keyword.equals(keyword)) {
				if (theLink.next == null) {
					return null;
				} else {
					theLink = theLink.next;
				}
			}
		} else {
			System.out.print(" ");
		}
		return theLink;
	}

	public void insertLast(String keyword) {
		MasterLink newLink = find(keyword);
		if (newLink == null) {
			newLink = new MasterLink(keyword);
			newLink.createBabyLink();
			if (isEmpty()) {
				first = newLink;
			} else {
				last.next = newLink;
				current.insertToBabyLinkList(keyword);
			}
			current = last = newLink;
			n++;
		} else {
			current.insertToBabyLinkList(keyword);
			current = newLink;
		}
	}

}
