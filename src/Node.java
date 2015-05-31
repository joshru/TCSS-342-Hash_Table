import java.util.Comparator;

/**
 * The Node Class.
 */
public class Node implements Comparator<Node> {
	
	/** My String. */
	public String myStr;
	
	/** My Frequency. */
	public int myFreq;
	
	/** My left node. */
	public Node left;
	
	/** My right node. */
	public Node right;
	
	/** My Huffman code. */
	public String myCode = "";

	/**
	 * Instantiates a new node.
	 *
	 * @param theStr the string
	 * @param theFreq the frequency
	 */
	public Node(String theStr, int theFreq) {
		myStr = theStr;
		myFreq = theFreq;
	}
	
	/**
	 * Instantiates a new node with left and right children.
	 *
	 * @param n1 the left node
	 * @param n2 the right node
	 */
	public Node(Node n1, Node n2) {
		left = n1;
		right = n2;
		myFreq = n1.myFreq + n2.myFreq;
				
	}
	
	/**
	 * Instantiates a new dummy node.
	 */
	public Node() {
		//Creates a dummy node for the comparator
	}
	
	/**
	 * Checks if node is a leaf.
	 *
	 * @return true, if is leaf
	 */
	public boolean isLeaf() {
		return (left == null && right == null);
	}
	
	/**
	 * Returns the node's string and frequency in string format.
	 */
	public String toString() {
		return "" + "'" + myStr + "'" + "=" + myFreq;
	}

	/**
	 * Compares the frequency of two nodes.
	 * 
	 * @param n1 the first node
	 * @param n2 the second node
	 * @return int, the diffrence in the two frequencies. 
	 */
	@Override
	public int compare(Node n1, Node n2) {
		// TODO Auto-generated method stub
		return n1.myFreq - n2.myFreq;
	}
}
