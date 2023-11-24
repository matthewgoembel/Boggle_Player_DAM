# Boggle_Player_DAM

IDEAS 11/21/2023 Anthony Clayton

Data Structures:
  Trie: Store the dictionary of words in a trie data structure. Tries are efficient for word lookups and can handle prefix matches well.
    Construction from Dictionary:
      Read each word from the dictionary and insert it into the trie.
      Start from the root node and insert each character of the word into the trie, creating new nodes as needed.

  2D Array or Graph: Represent the 4x4 board using a 2D array or a graph structure to efficiently traverse adjacent letters.

Algorithm:
  Board Traversal: Use depth-first search (DFS) or breadth-first search (BFS) to traverse the board, checking for valid words.
    Searching for Words:
      During the Boggle board traversal, use the trie to check if the formed sequence of characters exists in the trie.
      Use a depth-first search (DFS) or similar traversal method to explore adjacent letters on the board.
      As you form a sequence of characters during traversal, check if that sequence forms a valid word by searching the trie.
      
  Word Validation: During the traversal, check if the formed sequences match any word in the trie. Apply the rules of adjacent letters and the 'QU' combination.
  Score Calculation: Keep track of valid words and their scores according to the given scoring system.
  Memory Management: Optimize memory usage by avoiding unnecessary storage.

MORE IDEAS 11/23/2023 Matthew Goembel

Data Structure:
  Trie: Stores the whole dictionary. Trie allows for O(log(n)) traversal time and O(n) space worst case.
    - Compression by common Prefixes (nodes with only one child) allows for < O(n) space
    - Have a variable as the value V, that is true if the node is the end of a word. 
  OR
  Hashset: Stores the whole dictionary by unique Key. Allows for O(1) lookup with O(n) space. at worst.
  2D Array: Represents the 4x4 Boggle board.
  
Algotithms:
  Trie traversal:
    1. Starting at the root, DFS each charchter in the boggle pattern, follow the coorosonding edges of the trie. 
    2. If the edge DNE, stop. If the pattern is found & is marked as a word, success. Otherwise, repeat for the next character
    3. O(m), where m is boggle pattern length (Each char in the pattern is looked at once).
  OR
  Hashset traversal:
    1. Compare Key value
    2. If it matches, word is found. Otherwise word DNE.
    
  Letter Checking: Use an Array indicies to check for adjacnency
  Score: Calculated and changed acordingly after finding a word
  Optimization Challnge: Minimize storage, without sacrificing program speed.
  
Overall: Trie would offer most efficiency in this senario. However, must:
  1. Optimize for prefix checking
  2. Avoid redundant computation / unessesary recusion
