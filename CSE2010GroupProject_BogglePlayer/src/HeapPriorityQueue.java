import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

interface Entry<K,V> {
	  /**
	   * Returns the key stored in this entry.
	   * @return the entry's key
	   */
	  K getKey();

	  /**
	   * Returns the value stored in this entry.
	   * @return the entry's value
	   */
	  V getValue();
}

class DefaultComparator<E> implements Comparator<E> {
/**
* Compares two elements.
*
* @return a negative integer if <tt>a</tt> is less than <tt>b</tt>,
* zero if <tt>a</tt> equals <tt>b</tt>, or a positive integer if
* <tt>a</tt> is greater than <tt>b</tt>
*/
	@SuppressWarnings({"unchecked"})
	public int compare(E a, E b) throws ClassCastException {
	return ((Comparable<E>) a).compareTo(b);
	}
}


public class HeapPriorityQueue<K,V> {
  /** primary collection of priority queue entries */
  private ArrayList<Entry<K,V>> heap;
  private Comparator<K> comp = new DefaultComparator<K>();

  /** Creates an empty priority queue based on the natural ordering of its keys. */
  public HeapPriorityQueue() {
	  heap = new ArrayList<>();
  }

  // protected utilities
  private int parent(int j) { return (j-1) / 2; }     // truncating division
  private int left(int j) { return 2*j + 1; }
  private int right(int j) { return 2*j + 2; }
  private boolean hasLeft(int j) { return left(j) < heap.size(); }
  private boolean hasRight(int j) { return right(j) < heap.size(); }

  /** Exchanges the entries at indices i and j of the array list. */
  protected void swap(int i, int j) {
    Entry<K,V> temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  /** Moves the entry at index j higher, if necessary, to restore the heap property. */
  protected void upheap(int j) {
    while (j > 0) {            // continue until reaching root (or break statement)
      int p = parent(j);
      if (comp.compare(heap.get(j).getKey(), heap.get(p).getKey()) >= 0) break; // heap property verified
      swap(j, p);
      j = p;                                // continue from the parent's location
    }
  }

  /** Moves the entry at index j lower, if necessary, to restore the heap property. */
  protected void downheap(int j) {
    while (hasLeft(j)) {               // continue to bottom (or break statement)
      int leftIndex = left(j);
      int smallChildIndex = leftIndex;     // although right may be smaller
      if (hasRight(j)) {
          int rightIndex = right(j);
          if (comp.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0)
            smallChildIndex = rightIndex;  // right child is smaller
      }
      if (comp.compare(heap.get(smallChildIndex).getKey(), heap.get(j).getKey()) >= 0)
        break;                             // heap property has been restored
      swap(j, smallChildIndex);
      j = smallChildIndex;                 // continue at position of the child
    }
  }

  /** Performs a bottom-up construction of the heap in linear time. */
  protected void heapify() {
    int startIndex = parent(size()-1);    // start at PARENT of last entry
    for (int j=startIndex; j >= 0; j--)   // loop until processing the root
      downheap(j);
  }

  // public methods

  /**
   * Returns the number of items in the priority queue.
   * @return number of items
   */
  public int size() { return heap.size(); }

  /**
   * Returns (but does not remove) an entry with minimal key.
   * @return entry having a minimal key (or null if empty)
   */
  public Entry<K,V> min() {
    if (heap.isEmpty()) return null;
    return heap.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   * @param key     the key of the new entry
   * @param value   the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);      // auxiliary key-checking method (could throw exception)
    Entry<K,V> newest = new PQEntry<>(key, value);
    heap.add(newest);                      // add to the end of the list
    upheap(heap.size() - 1);               // upheap newly added entry
    return newest;
  }

  /**
   * Removes and returns an entry with minimal key.
   * @return the removed entry (or null if empty)
   */
  public Entry<K,V> removeMin() {
    if (heap.isEmpty()) return null;
    Entry<K,V> answer = heap.get(0);
    swap(0, heap.size() - 1);              // put minimum item at the end
    heap.remove(heap.size() - 1);          // and remove it from the list;
    downheap(0);                           // then fix new root
    return answer;
  }

@SuppressWarnings("hiding")
class PQEntry<K,V> implements Entry<K,V> {
    private K k;  // key
    private V v;  // value

    public PQEntry(K key, V value) {
      k = key;
      v = value;
    }

    // methods of the Entry interface
    public K getKey() { return k; }
    public V getValue() { return v; }

    // utilities not exposed as part of the Entry interface
    private void setKey(K key) { k = key; }
    private void setValue(V value) { v = value; }
  } //----------- end of nested PQEntry class -----------


  /** Determines whether a key is valid. */
  public boolean checkKey(K key) throws IllegalArgumentException {
    try {
      return (comp.compare(key,key) == 0);  // see if key can be compared to itself
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Incompatible key");
    }
  }

  /**
   * Tests whether the priority queue is empty.
   * @return true if the priority queue is empty, false otherwise
   */
  public boolean isEmpty() { return size() == 0; }

}
