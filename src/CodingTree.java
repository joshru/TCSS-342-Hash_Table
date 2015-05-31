import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * The Class CodingTree.
 */
public class CodingTree {
	
	/** The root. */
	public Node root;
	
	/** The my q. */
	public PriorityQueue<Node> myQ;
	
	/** The frequency map. */
	public MyHashTable<String, Integer> frequencyMap;
	
	/** The codes. */
	public MyHashTable<String, String> codes;
	
	/**
	 * Instantiates a new coding tree.
	 *
	 * @param theMessage the the message
	 */
	public CodingTree(String theMessage) {
		Comparator<Node> comp = new Node();
		
		root = null;
		myQ = new PriorityQueue<Node>(256, comp);
		frequencyMap = new MyHashTable<String, Integer>((int)Math.pow(2, 14));
		codes = new MyHashTable<String, String>((int)Math.pow(2, 14));
		
		createWordFrequencyMap(theMessage);
		buildQueue();
		buildTree();

	}
	
	/**
	 * Creates the word frequency map.
	 *
	 * @param theStr the the str
	 */
	public void createWordFrequencyMap(String theStr) {
		StringBuilder sb = new StringBuilder();
		
		char chars[] = theStr.toCharArray();
		int i = 0;
		
		while(i < chars.length) {
			
			if (inWord(chars[i])) {
				while(inWord(chars[i])) {
					sb.append(chars[i++]);
				}
			} else {
				sb.append(chars[i++]);
			}
			
			if(!frequencyMap.containsKey(sb.toString())) {
				frequencyMap.put(sb.toString(), 1);
				
			} else if (frequencyMap.containsKey(sb.toString())){
				int count = frequencyMap.get(sb.toString());
				frequencyMap.put(sb.toString(), ++count);
			}
			
			sb = new StringBuilder();
		}	
	}
	
	/**
	 * In word.
	 *
	 * @param theC the the c
	 * @return true, if successful
	 */
	public boolean inWord(char theC) {
		if ((theC <= 'Z' && theC >= 'A') || (theC <= 'z' && theC >= 'a') || (theC <= '9' && theC >= '0') || theC == '\'' || theC == '-') {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Recursive encode.
	 *
	 * @param root the root
	 * @param code the code
	 */
	public void recursiveEncode(Node root, String code) {
		if (root.left != null) {
			recursiveEncode(root.left, code + '0');
		}
		if (root.right != null) {
			recursiveEncode(root.right, code + '1');
		}
		if (root.isLeaf()) {
			codes.put(root.myStr, code);
		}
	}
	
	/**
	 * Builds the queue.
	 */
	private void buildQueue() {
		for (WordCode<String, Integer> wc : frequencyMap.entrySet()) {
			Node temp = new Node(wc.myKey, wc.myValue);
			myQ.offer(temp);
		}
	}
	
	/**
	 * Builds the tree.
	 */
	private void buildTree() {
		while (myQ.size() > 1) {
			Node left = myQ.poll();
			Node right = myQ.poll();
			myQ.offer(new Node(left, right));
			
		}
		root = myQ.poll();
		recursiveEncode(root, "");
	}

}
