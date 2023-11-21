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
