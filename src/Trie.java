import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Trie {
	private class TrieNode {
		boolean isMarked;
		Map<Character, TrieNode> mapOfChildren;
		private char character;

		TrieNode() {
			this.isMarked = false;
			this.mapOfChildren = new HashMap<>();
			this.character = getCharacter();
		}

		public char getCharacter() {
			return this.character;
		}

	}

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
// root can represent an empty string
	}

	public void insert(String word) {
		if (word == null || word.length() == 0) {
			return;
		}

		TrieNode parent = root;
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);

			TrieNode child = parent.mapOfChildren.get(character);
			// Check if having a TrieNode associated with 'character'
			if (child == null) {
				child = new TrieNode();
				parent.mapOfChildren.put(character, child);
				child.character = character;

			}

			parent = child; // Navigate to next level
		}
		parent.isMarked = true;

	}

	public boolean contains(String word) {
		if (word == null) { // Assume that empty string is in the trie
			return false;
		}

		TrieNode parent = root;
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);

			TrieNode child = parent.mapOfChildren.get(character); // Check if having a TrieNode associated with
																	// 'character'
			if (child == null) { // null if 'word' is way too long or its prefix doesn't appear in the Trie
				return false;
			}

			parent = child; // Navigate to next level
		}

		return parent.isMarked;
	}

	public TrieNode getNode(String prefix) {
		if (prefix == null) {
			return null;
		}

		TrieNode parent = root;
		for (int i = 0; i < prefix.length(); i++) {
			char character = prefix.charAt(i);

			TrieNode child = parent.mapOfChildren.get(character); // Check if having a TrieNode associated with
																	// 'character'
			if (child == null) { // null if 'prefix' is way too long or its prefix doesn't appear in the Trie
				return null;
			}

			parent = child; // Navigate to next level
		}

		return parent;
	}

	public boolean isPrefix(String prefix) {
// Assume that empty string is a valid prefix
		if (prefix == null) {
			return false;
		}

		TrieNode parent = root;
		for (int i = 0; i < prefix.length(); i++) {
			char cur = prefix.charAt(i);

			TrieNode child = parent.mapOfChildren.get(cur); // Check if having a TrieNode associated with 'cur'
			if (child == null) { // null if 'prefix' is way too long or its prefix doesn't appear in the Trie
				return false;
			}

			parent = child; // Navigate to next level
		}

		return true;
	}

	public boolean delete(String dword) {
		try {
		if (dword == null || dword.length() == 0) {
			return false;
		}

		// All nodes below 'deleteBelow' and on the path starting with 'deleteChar'
		// (including itself) will be deleted if needed
		TrieNode deleteBelow = null;
		char deleteChar = '\0';

		// Search to ensure word is present
		TrieNode parent = root;
		for (int i = 0; i < dword.length(); i++) {
			char character = dword.charAt(i);

			TrieNode child = parent.mapOfChildren.get(character); // Check if having a TrieNode associated with
																	// 'character'
			if (child == null) { // null if 'dword' is way too long or its prefix doesn't appear in the Trie
				return false;
			}

			if (parent.mapOfChildren.size() > 1 || parent.isMarked) { // Update 'deleteBelow' and 'deleteChar'
				deleteBelow = parent;
				deleteChar = character;
			}

			parent = child;
		}

		if (!parent.isMarked) { // dword isn't in trie
			return false;
		}

		if (parent.mapOfChildren.isEmpty()) {
			deleteBelow.mapOfChildren.remove(deleteChar);
		} else {
			parent.isMarked = false; // Delete dword by mark it as not the end of a word
		}

		return true;
	}
		catch(Exception NullPointerException){
			root.mapOfChildren.clear();
			return true;
		}
		}
	

	boolean isEmpty() {
		return root.mapOfChildren.isEmpty(); // Check if the root is empty then the whole trie is empty to.
	}

	void clear() {
		root.mapOfChildren.clear(); // When we clear the root the whole trie will be clear
	}

	int size() {
		return size(root);
	}

	private int size(TrieNode parent) {
		int temp = 0;
		if (parent == null)
			return 0;
		else
			for (char charcter : parent.mapOfChildren.keySet())
				temp = temp + 1 + size(parent.mapOfChildren.get(charcter)); // counting evrey uniqe char in the trie
		return temp;
	}

	public String[] allWordsPrefix(String pref) {
		if ((getNode(pref) == null))
			return new String[0]; // returning an empty array.
		String temporary = pref.substring(0, pref.length() - 1);
		if (pref.length() > 1)
			return helper(temporary, getNode(pref)).split(" ");//split used here to return an array instead of string
		else
			return helper("", getNode(pref)).split(" ");

	}

	private String helper(String prefix, TrieNode node) {

		ArrayList<String> arrayOfChar = new ArrayList<>();

		if (node.isMarked) { // check if it is marked as a whole word
			arrayOfChar.add(prefix + node.getCharacter());
		}

		for (char character : node.mapOfChildren.keySet())
//			iterate for each character in the trie
			arrayOfChar.add(helper(prefix + node.getCharacter(), node.mapOfChildren.get(character)));
		return String.join(" ", arrayOfChar);
	}
}

