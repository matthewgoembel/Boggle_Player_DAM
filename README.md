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
  Hashset: Stores the whole dictionary by unique Key. Allows for O(1) lookup with O(n) space at worst.
  2D Array: Represents the 4x4 Boggle board.
  
Algorithms:
  Trie traversal:
    1. Starting at the root, DFS each char in the boggle pattern, follow the corresponding edges of the trie. 
    2. If the edge DNE, stop. If the pattern is found & is marked as a word, success. Otherwise, repeat for the next character
    3. O(m), where m is boggle pattern length (Each char in the pattern is looked at once).
  OR
  Hashset traversal:
    1. Compare Key value
    2. If it matches, word is found. Otherwise word DNE.
    
  Letter Checking: Use an Array indices to check for adjacency
  Score: Calculated after adding up all word points
  Optimization Challenge: Minimize storage, without sacrificing program speed.
  
Overall: Trie would offer most efficiency in this senario. However, must:
  1. Optimize for prefix checking
  2. Avoid redundant computation / unnecessary recursion

IDEAS 11/27/2023 Darian Dean

Data Structure: 
  Trie – Efficient for prefix searches of words and within the scope of the problem, efficient for word lookups. Building the Trie – Insert 
  each word from the given dictionary into the Trie. The total time complexity for constructing the Trie is O(nm), where n is the number of words 
  in the dictionary and m is the length of the word.

  Boggle Board Structure: 2D array – represents 4x4 boggle game board.

Algorithms: 
  1. Trie Insertion – Iterate through each character of the word, traverse the Trie, and create new Trie nodes if necessary. Time complexity: O(m) m = length of the word.
  2. Word lookup – Explores neighboring cells on the board using DFS, updating the current word and checking if it makes a valid word present in the Trie.
  3. DFS – Depth-First Search algorithm, recursively explores adjacent cells on the board. Or use a stack to explore adjacent cells.

 - During word lookup, if a word matches a word in the Trie, store that word/location and its score. 
 - Optimize for memory usage
