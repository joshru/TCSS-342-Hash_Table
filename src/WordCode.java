
/**
 * The Wrapper Class WordCode.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class WordCode<K, V> {

	/** My key. */
	public K myKey;
	
	/** My value. */
	public V myValue;
	
	/**
	 * Instantiates a new word code.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public WordCode(K key, V value) {
		myKey = key;
		myValue = value;
	}
	
	/**
	 * Returns a string containing the key and value of the object.
	 */
	public String toString() 	{
		return "" + myKey + " = " + myValue;
	}
	
}
