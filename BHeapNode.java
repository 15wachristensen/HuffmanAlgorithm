// Walker Christensen

// represents an item in the priority queue
public class BHeapNode{
		int priority;
		HuffmanTree data;
		// stores information about the huffman tree for this node
		// and the frequency 
		// lowest frequency has highest priority
		public BHeapNode(int p, HuffmanTree d){
			this.priority = p;
			this.data = d;
		}
	}