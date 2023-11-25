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

import java.util.*;


public class BogglePlayer {
    // Var alphabet size
    // Var TrieNode array of children
    // Var endOfAWord
    // Var TrieNode rootNode

    // initialize BogglePlayer with a file of English words
    public BogglePlayer(String wordFile) {
	// Initiate children of root (A-Z)
	// Set endOfAWord for each node
    }

    // Insert(word) method O(word_length):
    // 	  Each charchter of a word is an individual Trie node
    //    For each charcter of the word:
    // 	      Construct new nodes of the word. Trie depth = word_length.
    //        If word is a prefix of an existing word/Node already exists, 
    //        combind non-new nodes as a prefix, mark remainder of word as endOfAWord
    //        Mark final nodes at endOfAWord
    // 
    // Seach(word) method O(word_length):
    //     Compares current charchter and moves down the trie
    //     if we reach endOfAWord the word exists --> add points, compare highest 20 words
    //     if current char DNE, stop searching, word cannot exist
    // 
    // 
    // 
    // based on the board, find valid words
    //
    // board: 4x4 board, each element is a letter, 'Q' represents "QU", 
    //    first dimension is row, second dimension is column
    //    ie, board[row][col]     
    //
    // Return at most 20 valid words in UPPERCASE and 
    //    their paths of locations on the board in myWords;
    //    Use null if fewer than 20 words.
    //
    // See Word.java for details of the Word class and
    //     Location.java for details of the Location class

    public Word[] getWords(char[][] board)
    {
	Word[] myWords = new Word[20];






        return myWords;
    }

}
