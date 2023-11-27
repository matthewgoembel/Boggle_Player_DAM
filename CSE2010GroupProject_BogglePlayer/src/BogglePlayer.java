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

// class TrieNode?
// ...

public class BogglePlayer {
    // Var alphabet size
    // Var TrieNode array of children
    // Var endOfAWord
    // Var TrieNode rootNode
    // Var points

    // initialize BogglePlayer with a file of English words
    public BogglePlayer(String wordFile) {
	// Initiate children of rootNode (A-Z)
	// Set endOfAWord for each TrieNode
    }

    // Insert(word) method O(word_length):
    // 	  Each char of a word is an individual Trie node
    //    For each char of the word:
    // 	      Construct new nodes of the word. Trie depth = word_length.
    //        If word is a prefix of an existing word/Node already exists, 
    //        combine non-new nodes as a prefix, mark remainder of word as endOfAWord
    //        Mark final nodes at endOfAWord
    // 
    // Seach(word) method O(word_length):
    //     Compares current char and moves down the trie
    //     If we reach endOfAWord the word exists
    //     If current char DNE, stop searching, word cannot exist
    // 
    // Calculate Points: after top 20 words are found, add each 
    //     word's rspective points to total score
    // 
    // Based on the board, find valid words
    //
    // Board: 4x4 board, each element is a letter, 'Q' represents "QU", 
    //    first dimension is row, second dimension is column
    //    ie, board[row][col]     
    //
    // Return at most 20 valid words in UPPERCASE and 
    //    their paths of locations on the board in myWords;
    //    Use null if fewer than 20 words.
    //
    // See Word.java for details of the Word class and
    //    Location.java for details of the Location class

    public Word[] getWords(char[][] board) {
	Word[] myWords = new Word[20];
	    
        // Var Visited: [][] tracks visited cells
        // DFS through each position on the board
        //     If the last char of the current word is endOfWord, found, add the word
        //     Recurr the DFS search along all <=8 possible paths, up to max word length (8 for optimal score),
	//     from the current cell, but NOT duplicating paths
        // Sort array by longest word_length, and return those words
        //        

        return myWords;
    }

}
