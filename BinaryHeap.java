// Walker Christensen
public class BinaryHeap {
	private int size;
	private BHeapNode[] nodeArray;
	// represents a priority queue
	public BinaryHeap(int numChars){
		size = 0;
		nodeArray = new BHeapNode[numChars+1];
	}
	// inserts a BHeapNode into the binary heap
	
	public void insert(int freq, HuffmanTree ht){
		int parent; 
		int child; 
		size++;
		child = size;
		parent = child/2;
		BHeapNode placeMe = new BHeapNode(freq, ht);
		nodeArray[0] = placeMe;
		while(nodeArray[parent].priority > freq){
			nodeArray[child] = nodeArray[parent];
			child = parent; 
			parent = child/2;
		}
		nodeArray[child] = placeMe;
	}
	// PRE: !empty()
	public BHeapNode removeMin(){
		int parent; 
		int child; 
		BHeapNode min = nodeArray[1];
		BHeapNode lastSpot = nodeArray[size];
		size--;
		child = 2;
		while(child <= size){
			parent = child/2;
			if(child < size && nodeArray[child+1].priority < nodeArray[child].priority){
				child++;
			}
			if(lastSpot.priority < nodeArray[child].priority){break;}
			nodeArray[parent] = nodeArray[child];
			child = 2*child;
		}
		nodeArray[child/2] = lastSpot;
		return min;
	}
	public boolean empty(){return size == 0;}
	public int getSize(){return size;}
	
}
