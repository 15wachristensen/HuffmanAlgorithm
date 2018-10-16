import java.io.*;
// Walker Christensen
import java.util.*;
public class HuffmanEncode {
	private int size;
	private int[] frequencyValues;
	private String[] encodingsInTree;
	private BufferedReader bf;
	private BinaryHeap bh;
	private HuffmanTree ht; 
	private char specChar = (char)128;
	private HuffmanOutputStream hos;
	// Algorithm for Encoding a file using 
	// Huffman Encoding
	public HuffmanEncode(String in, String out){
		frequencyValues = new int[128];
		encodingsInTree = new String[128];
		findFrequencies(in);
		bh = new BinaryHeap(size);
		putInFrequencies(bh);
		buildHuffTree();
		traverseTree();
		hos = new HuffmanOutputStream(out, ht.toString(), NumCharsInHuffTree());
		writeEncodingsToStream(in);
	}
	// Can be optimized: count chars as you read file the first time
	// Keeps count of the number of chars in the original file
	private int NumCharsInHuffTree(){		
			int sum = 0;
			for(int i: frequencyValues){
				sum = sum+i;
			}
			return sum;
	}
	// Goes through the encodingsInTree array and writes individual bits.
	// Uses writeBit, which sends a 0 or 1 depending on the encoding

	public void writeEncodingsToStream(String in){
		try{
			BufferedReader br = new BufferedReader(new FileReader(in));
			while(true){
				int characterNextInLine = br.read();
				if(characterNextInLine == -1){
					break;
				}
				String mew = encodingsInTree[characterNextInLine];
				for(int i = 0; i < mew.length(); i++){
					int bit = (int)mew.charAt(i)-(int)'0';
					int mew2 = (bit);
					hos.writeBit(mew2);
				}
			}
			br.close();
			hos.close();
		}catch(IOException i){
			System.out.println("File not found");
		}
	}
	// Creates encodings for the tree
	private void traverseTree(){
		Iterator<String> iter = ht.iterator();
		while(iter.hasNext()){
			String encoding = iter.next();
			char placeInArray = encoding.charAt(encoding.length()-1);
			String mewEncoding = encoding.substring(0, encoding.length()-1);
			encodingsInTree[(int)placeInArray] = mewEncoding;
		}
	}
	// Creates a HuffmanTree object from the priority queue information
	private void buildHuffTree(){
		while(bh.getSize() > 1){
			BHeapNode temp1 = bh.removeMin();
			BHeapNode temp2 = bh.removeMin();
			bh.insert(temp1.priority+temp2.priority, new HuffmanTree(temp1.data, temp2.data, specChar));
		}
		ht = bh.removeMin().data;
	}
	// puts frequency and huffman tree information into priority queue
	private	void putInFrequencies(BinaryHeap bh){
		for(int i = 0; i < frequencyValues.length; i++){
			if(frequencyValues[i] != 0){
				char c = (char)i;
				int fv = frequencyValues[i];
				bh.insert(fv, new HuffmanTree(c));
			}
		}
	}
	// Can be optimized: 
	// Goes through file and calculates the number of times a character
	// 0-127 ACII appears in the file and puts into frequencyValues
	private void findFrequencies(String in){
		try{
			bf = new BufferedReader(new FileReader(in));
			while(true){
				int placeInArray = bf.read();
				if(placeInArray  == -1){
					break;
				}else{
					if(frequencyValues[placeInArray] == 0){
						size++;
					}
					frequencyValues[placeInArray]++;
				}
			}
			bf.close();
			}catch(FileNotFoundException f){
				System.out.println("Couldn't find file");
			}catch(Exception e){
				System.out.println(e.getStackTrace());
			}
	}
	public static void main(String args[]){
		new HuffmanEncode(args[0], args[1]);
	}
}
