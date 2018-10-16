import java.io.*;
// Walker Christensen
import java.util.*;
public class HuffmanDecode {
	private HuffmanInputStream hts;
	private String s;
	private HuffmanTree ht;
	// represents a char I know won't be in the file
	private char specChar = (char)128;
	// Record of how many chars were in the original file
	private int numChars;
	
	public HuffmanDecode(String in, String out){
		// Implements the huffman decoding algorithm
		hts = new HuffmanInputStream(in);
		s = hts.getTree();
		ht = new HuffmanTree(s, specChar);
		numChars = hts.totalChars();
		readMyBits(out);
	}
	// Can still be optimized, duplicate code
	// Makes it seem like we can read individual bits in file
	// every bit moves you down the tree until you find a character
	private void readMyBits(String out){
		try{
			BufferedWriter bf = new BufferedWriter(new FileWriter(out));
			ht.moveRoot();
			int count = 0;
			while(count < numChars){
				int n = hts.readBit();
				if(n == 0){
					ht.moveLeft();
					if(ht.atLeaf()){
						bf.write(ht.current());
						count++;
						ht.moveRoot();
					}
				}else{
					ht.moveRight();
					if(ht.atLeaf()){
						bf.write(ht.current());
						count++;
						ht.moveRoot();
					}
				}
			}
			bf.close();
		}catch(IOException e){
			System.out.println("readMyBits");
		}
	}
	public static void main(String[] args){
		new HuffmanDecode(args[0], args[1]);
	}
}
