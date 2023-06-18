import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class testTrie {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(System.in);
		Scanner sT = new Scanner(System.in);
		Scanner file = new Scanner(new File("dictionary.txt"));
		System.out.println(
				"TRIE PROJECT: Enter your choice ?\n    1)Create an empty trie\n    2)Create a trie with initial letters\n");
		boolean f = true;

		while (f) {
			int choice = s.nextInt();
			if (choice == 1) {
				Trie trie = new Trie();
				
				System.out.println("Has been created empty trie");

				while (f) {
					System.out.println(
							"TRIE PROJECT: Enter your choice?\n    3)Insert a word\n    4)Delete a word\n    5)List of all words that begin with a prefix\n    6)Size of the array\n    7)End\n");

					choice = s.nextInt();
					if (choice == 3) {
						System.out.print("Enter word to insert ");
						String sd =s.next().toUpperCase();
						if(trie.contains(sd)) {
							System.out.println("The word is already in the trie.");
						}
						else {
							trie.insert(sd);
							System.out.println("DONE!");
						}
					} else if (choice == 4) {
						System.out.print("Enter word to delete ");
						if(trie.delete(s.next()))
							System.out.println("Word deleted successfully.");
						else
							System.out.println("This word dose not exist in the trie");
					} else if (choice == 5) {
						System.out.print("Enter the prefix ");
						System.out.println("List of all word with this prefix: "
								+ Arrays.toString(trie.allWordsPrefix(s.next().toUpperCase())) + "\n");
					} else if (choice == 6) {
						int si=trie.size();
						if(si!=0)
						System.out.println("The trie size is " + si);
						else
							System.out.println("The trie is empty.");
					} else if (choice == 7) {
						f = false;
						System.out.println("Thank you :)");
					} else
						System.out.println("Wrong input :/");
				}
			}

			else if (choice == 2) {
				Trie trie = new Trie();
				System.out.print("Enter your List of letter: ");
				String tmp = sT.nextLine().replace(" ", "").toUpperCase();
				char hh[] = tmp.toCharArray();
				while (file.hasNext()) {
					int count = 0;
					String word = file.next();
					if (check(hh, tmp, word, count)) {

						trie.insert(word);
					}
				}

				while (f) {
					System.out.println(
							"TRIE PROJECT: Enter your choice?\n    3)Insert a word\n    4)Delete a word\n    5)List of all words that begin with a prefix\n    6)Size of the array\n    7)End\n");

					choice = s.nextInt();
					if (choice == 3) {
						System.out.print("Enter word to insert ");
						trie.insert(s.next().toUpperCase());
						System.out.println("DONE!");
					} else if (choice == 4) {
						System.out.print("Enter word to delete ");
						if(trie.delete(s.next()))
							System.out.println("Word deleted successfully.");
						else
							System.out.println("This word dose not exist in the trie");
					} else if (choice == 5) {
						System.out.print("Enter the prefix ");
						System.out.println("List of all word with this prefix: "
								+ Arrays.toString(trie.allWordsPrefix(s.next().toUpperCase())) + "\n");
					} else if (choice == 6) {
						int si=trie.size();
						if(si!=0)
						System.out.println("The trie size is " + si);
						else
							System.out.println("The trie is empty.");
					} else if (choice == 7) {
						f = false;
						System.out.println("Thank you :)");
					} else
						System.out.println("Wrong input :/");
				}
			}
		}

		s.close();
		file.close();
		sT.close();
	}

	public static int counter(String word, char i) {
		int count = 0;
		for (int v = 0; v < word.length(); v++) {
			char tmp = word.charAt(v);
			if (tmp == i)
				count++;
		}

		return count;
	}

	public static boolean check(char[] userarrayinput, String userinput, String fileword, int count) {
		String word1 = "";
		String word2 = "";
		if (fileword.matches("[" + userinput + "]{0," + userinput.length() + "}")) {
			for (char x : userarrayinput) {
				if (counter(fileword, x) > 1) {
					if (counter(word2, x) < counter(fileword, x)) {//this step helps to make sure that the duplicated char in the fileword dose not counted more than what is required 
						word2 += x;
						count++;
					}
				} else if (counter(fileword, x) == 1) {
					if (!word1.contains(x + "")) {
						word1 += x;
						count++;
					}
				}
			}
			if (count == fileword.length()) {
				return true;
			}

		}
		return false;

	}
}
