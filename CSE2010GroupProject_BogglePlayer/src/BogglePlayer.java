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

    static final int SIZE = 26; // Alphabet Size
    static final int M = 4;     // Board Width
    static final int N = 4;     // Board Length

    static class TrieNode {
        TrieNode[] children = new TrieNode[SIZE];
        boolean leaf;           // If node is the end of a word

        public TrieNode() {
            // Initialize children
            leaf = false;
            for (int i = 0; i < SIZE; i++)
                children[i] = null;
        }
    }
    // Starting node
    static TrieNode root = new TrieNode();
    // Stores top 20 found words
    static HeapPriorityQueue<Integer, Word> heapPQ = new HeapPriorityQueue<Integer, Word>();
    // Ensures uniquness among words
    static Set<String> uniqueWords = new HashSet<String>();
    // Tracks path of each found word
    static ArrayList<Location> flocations = new ArrayList<>();

    // Construct a trie using given wordFile
    public BogglePlayer(String wordFile) {
        buildTrie(wordFile);
    }

    /**
     * Buffered reader reads one word at a time
     * Each char of the current word is added as a child at its alphabet index
     * Final char of the word, leaf is set to true
     * @param wordFile file of english words
     */
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

    /**
     * Checks if the cell(i, j) is a valid board position
     * and has not been visited before
     * @param i current row
     * @param j current column
     * @param visited Matrix of boolean locations
     * @return true, if the cell is valid and unvisited
     */
    static boolean isSafe(int i, int j, boolean[][] visited) {
        return (i >= 0 && i < M && j >= 0 && j < N && !visited[i][j]);
    }

    /**
     * ...
     * @param root current TrieNode
     * @param i current row
     * @param j current col
     * @param str Search string
     */
    static void searchWord(TrieNode root, char[][] boggle, int i, int j, boolean[][] visited,
                           String str, HeapPriorityQueue<Integer, Word> heapPQ, ArrayList<Location> flocations) {
        // Mark the current cell as visited
        visited[i][j] = true;
        
        // Add the current location to the path
        flocations.add(new Location(i, j));
        
        Word currentWord = new Word(str);
        // Checks if the word passes all requirements to the PQ.
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

        // All possible search directions
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < 8; k++) {
            int newRow = i + rowOffsets[k];  // Next cell row
            int newCol = j + colOffsets[k];  // Next cell col

            // Check if the new cell is safe to visit
            if (isSafe(newRow, newCol, visited) && root.children[boggle[newRow][newCol] - 'A'] != null) {
                // 'Qu' special case
                if (boggle[newRow][newCol] == 'Q') {
                    searchWord(root.children['Q' - 'A'], boggle, newRow, newCol, visited, str + "QU", heapPQ, flocations);
                } else {
                    searchWord(root.children[boggle[newRow][newCol] - 'A'], boggle, newRow, newCol,
                            visited, str + boggle[newRow][newCol], heapPQ, flocations);
                }
            }
        }

        // Mark current element unvisited and remove current location from the path
        visited[i][j] = false;
        flocations.remove(flocations.size() - 1);
    }

    /**
     * ...
     * @param boggle boggle game board
     * @return array of 20 longest found words
     */
    public Word[] getWords(char[][] boggle) {
        
        Word[] myWords = new Word[20];
        // Initialize base variables
        boolean[][] visited = new boolean[M][N];
        
        TrieNode pChild = root; 
        
        StringBuilder str = new StringBuilder();
        
        heapPQ.insert(0, new Word(""));   

        // Traverse each matrix elements, and search for words
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // Check if a valid char
                if (pChild.children[(boggle[i][j]) - 'A'] != null) {
                    str.append(boggle[i][j]);
                    // Handle 'Qu' case
                    if (boggle[i][j] == 'Q') {
                        str.append('U');
                    }
                    searchWord(pChild.children[(boggle[i][j]) - 'A'],
                            boggle, i, j, visited, str.toString(), heapPQ, flocations);
                    str.setLength(0);  // Reset word to ""
                }
            }
        }
        // Top 20 words from the PQ
        int numWordsToCopy = Math.min(heapPQ.size(), 20);
        
        for (int i = 0; i < numWordsToCopy; i++)
            myWords[i] = heapPQ.removeMin().getValue();
        
        return myWords;
    }
}
