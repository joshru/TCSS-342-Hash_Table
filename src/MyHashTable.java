import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * The Class MyHashTable.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashTable<K, V> {

	/** My buckets. */
	public ArrayList<LinkedList<WordCode<K, V>>> myBuckets;
	
	/** My size. */
	public int mySize;
	
	/** The number entries. */
	private int numEntries;
	
	/**
	 * Instantiates a new hash table.
	 *
	 * @param capacity the capacity
	 */
	public MyHashTable(int capacity) {
		mySize = capacity;
		numEntries = 0;
		myBuckets = new ArrayList<LinkedList<WordCode<K, V>>>(mySize);
		
		for (int i = 0; i < mySize; i++) {
			myBuckets.add(new LinkedList<WordCode<K, V>>());
		}
	}

	/**
	 * Adds a new object to the hash table.
	 *
	 * @param searchKey the search key
	 * @param newValue the new value
	 */
	public void put(K searchKey, V newValue) {
		int index = hash(searchKey);
		
		Iterator<WordCode<K, V>> itr = myBuckets.get(index).iterator();
		
		while (itr.hasNext()) {
			WordCode<K, V> wc = itr.next();
			if (wc.myKey.equals(searchKey)) itr.remove();
			
		}
		myBuckets.get(index).add(new WordCode<K, V>(searchKey, newValue));
			
		numEntries++;
	}
	
	/**
	 * Gets the value associated with the search key.
	 *
	 * @param searchKey the search key
	 * @return the value
	 */
	public V get(K searchKey) {
		int index = hash(searchKey);
		
		Iterator<WordCode<K, V>> itr = myBuckets.get(index).iterator();
		while (itr.hasNext()) {
			WordCode<K, V> itrVal = itr.next();
			if (itrVal.myKey.equals(searchKey)) return itrVal.myValue;
		}
		
		return null;
	}
	
	/**
	 * Prints statistics about the hash table.
	 */
	public void stats() {
		System.out.println("Hash Table Stats");
		System.out.println("=================");
		System.out.println("Number of Entries: " + numEntries);
		System.out.println("Number of Buckets: " + myBuckets.size());
		System.out.println("Histogram of Bucket Sizes: " + histogram());
		System.out.printf("Fill Percentage: %.5f%%\n", fillPercent());
		System.out.printf("Average Non-Empty Bucket: %.7f\n\n", avgNonEmpty());		
	}
	
	/**
	 * Checks if the hash table contains the parameterized key.
	 *
	 * @param theKey the key
	 * @return true, if successful
	 */
	public boolean containsKey(K theKey) {
		boolean result = false;
		int index = hash(theKey);
			for (WordCode<K, V> wc : myBuckets.get(index)) {
				if (wc.myKey.equals(theKey)) result = true;
			}
		
		
		return result;
	}
	
	/**
	 * Returns all objects in the hash table.
	 *
	 * @return the array list of objects
	 */
	public ArrayList<WordCode<K, V>> entrySet() {
		ArrayList<WordCode<K, V>> entrySet = new ArrayList<>();
		
		for (LinkedList<WordCode<K, V>> ll : myBuckets) {
			for (WordCode<K, V> wc : ll) {
				entrySet.add(wc);
			}
		}
		return entrySet;
	}
	
	/**
	 * Returns a string containing all key value pairs in the hash table.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < myBuckets.get(i).size(); j++) {
				sb.append("(" + myBuckets.get(i).get(j).myKey + ", " + myBuckets.get(i).get(j).myValue + "), ");
			}
		}
		return sb.toString();
	}
	
	/**
	 * Hash function.
	 *
	 * @param key the key
	 * @return the int hash value
	 */
	private int hash(K key) {
		return key.hashCode() % (mySize / 2) + (mySize / 2);
	}
	
	/**
	 * Creates a histogram of filled buckets in the hash table.
	 *
	 * @return the string
	 */
	private String histogram() {
		StringBuilder sb = new StringBuilder("[");
		
		for (int i = 0; i < 8; i++) {
			int size = 0;
			for (int j = 0; j < myBuckets.size(); j++) {
				if (myBuckets.get(j).size() == i) size++;
			}
			sb.append(size + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}
	
	/**
	 * Finds and returns the fill percent.
	 *
	 * @return the double
	 */
	private double fillPercent() {
		double percent = 0;
		
		for (LinkedList<WordCode<K, V>> ll : myBuckets) {
			if (ll.size() != 0) percent++;
		}
		return  (percent / myBuckets.size()) * 100;
	}

	/**
	 * Finds and returns the average number of non empty buckets.
	 *
	 * @return the double
	 */
	private double avgNonEmpty() {
		double percent = 0;
		
		for (LinkedList<WordCode<K, V>> ll : myBuckets) {
			if (ll.size() != 0) percent++;
		}
		return  (numEntries / percent);
	}

}
