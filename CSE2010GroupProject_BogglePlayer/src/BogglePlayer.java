import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/*
  Authors:
  	Clayton, Anthony
  	Dean, Darian
	Goembel, Matthew

  Email addresses of group members:
  	aclayton2023@my.fit.edu
   	mgoembel2022@my.fit.edu
    ddean2022@my.fit.edu

  Group name: 34b

  Course: CSE 2010
  Section: 34

  Description of the overall algorithm and key data structures:


*/

public class BogglePlayer {

    static final int SIZE = 26;

    static final int M = 4;
    static final int N = 4;

    // trie Node
    static class TrieNode {
        TrieNode[] children = new TrieNode[SIZE];

        // isLeaf is true if the node represents
        // end of a word
        boolean leaf;

        // constructor
        public TrieNode() {
            leaf = false;
            for (int i = 0; i < SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode root = new TrieNode();
    static HeapPriorityQueue<Integer, Word> heapPQ = new HeapPriorityQueue<Integer, Word>();
    static Set<String> uniqueWords = new HashSet<String>();
    // initialize BogglePlayer with a file of English words
    // initialize boggle board
    public BogglePlayer(String wordFile) {
        buildTrie(wordFile);
    }

    // buildTrie(word):
    // 	 Each char of a word is an individual Trie node
    //    For each char of the word:
    //      Add new nodes only:
    //        If word is a prefix of an existing word/Node already exists,
    //        use same nodes as a prefix
    //        If not create new node
    //     Mark final nodes as endOfAWord
    public void buildTrie(String wordFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(wordFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.toUpperCase();
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    int charIndex = c - 'A';
                    if (node.children[charIndex] == null) {
                        node.children[charIndex] = new TrieNode();
                    }
                    node = node.children[charIndex];
                }
                node.leaf = true;
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your requirements
        }
    }

    static boolean isSafe(int i, int j, boolean[][] visited) {
        return (i >= 0 && i < M && j >= 0 && j < N && !visited[i][j]);
    }

    /**
     * Returns points for given word, based on length
     */
    static ArrayList<Location> flocations = new ArrayList<>();

    static void searchWord(TrieNode root, char[][] boggle, int i, int j, boolean[][] visited, 
    		String str, HeapPriorityQueue<Integer, Word> heapPQ, ArrayList<Location> flocations) {
        // Mark the current cell as visited
        visited[i][j] = true;

        // Add the current location to the path
        flocations.add(new Location(i, j));

        // if we found word in trie / dictionary
        Word currentWord = new Word(str);

        if (root.leaf && str.length() > 2 && !uniqueWords.contains(str)) {
            
        	if (heapPQ.size() < 20 || currentWord.getWord().length() > heapPQ.min().getKey()) {
                if (heapPQ.size() == 20) {
                	heapPQ.removeMin(); // Remove the word with the smallest length if the heap is full
                	uniqueWords.remove(str);
                }
        	
                heapPQ.insert(currentWord.getWord().length(), currentWord);
                currentWord.setPath(new ArrayList<>(flocations));
                uniqueWords.add(str);
        	}
        }

        // traverse all children of the current root
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < 8; k++) {
            int newRow = i + rowOffsets[k];
            int newCol = j + colOffsets[k];

            // Check if the new cell is safe to visit
            if (isSafe(newRow, newCol, visited) && root.children[boggle[newRow][newCol] - 'A'] != null) {
                // Handle 'Q' case more effectively
                if (boggle[newRow][newCol] == 'Q') {
                    searchWord(root.children['Q' - 'A'], boggle, newRow, newCol, visited, str + "QU", heapPQ, flocations);
                } else {
                    searchWord(root.children[boggle[newRow][newCol] - 'A'], boggle, newRow, newCol, 
                    		visited, str + boggle[newRow][newCol], heapPQ, flocations);
                }
            }
        }

        // Make the current element unvisited and remove the current location from the path
        visited[i][j] = false;
        flocations.remove(flocations.size() - 1);
    }

    // Board: 4x4 board, each element is a letter, 'Q' represents "QU",
    //    first dimension is row, second dimension is column
    //    ie, board[row][col]

    // Return at most 20 valid words in UPPERCASE and
    //    their paths of locations on the board in myWords;
    //    Use null if fewer than 20 words.
    //
    // See Word.java for details of the Word class and
    //    Location.java for details of the Location class

    public Word[] getWords(char[][] boggle) {
        Word[] myWords = new Word[20];
        // Mark all characters as not visited
        boolean[][] visited = new boolean[M][N];
        TrieNode pChild = root;

        StringBuilder str = new StringBuilder();
        
        heapPQ.insert(0, new Word(""));

        // traverse all matrix elements
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // we start searching for word in dictionary
                // if we found a character which is child
                // of Trie root
                if (pChild.children[(boggle[i][j]) - 'A'] != null) {
                    str.append(boggle[i][j]);
                    // Handle 'Q' case
                    if (boggle[i][j] == 'Q') {
                        str.append('U');
                    }
                    searchWord(pChild.children[(boggle[i][j]) - 'A'],
                            boggle, i, j, visited, str.toString(), heapPQ, flocations);
                    str.setLength(0);
                }
            }
        }
        int numWordsToCopy = Math.min(heapPQ.size(), 20);
        for (int i = 0; i < numWordsToCopy; i++) {
            myWords[i] = heapPQ.removeMin().getValue();
        }
        for (Word x : myWords) {
        	System.out.println(x.getWord());
        }
        return myWords;
    }
}
