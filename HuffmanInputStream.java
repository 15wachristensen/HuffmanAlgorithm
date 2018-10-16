import java.io.*;
// Walker Christensen
public class HuffmanInputStream extends BitInputStream{
	private String tree; 
	private int totalChars;
	private int[] potentialByte;
	private int counter;
	private int byteRead;
	public HuffmanInputStream(String filename){
		super(filename);
		try{
			tree = d.readUTF();
			totalChars = d.readInt();
			potentialByte = new int[8];
			// loads bits into potentialByte array to send
			putBitsInArray(potentialByte);
			counter = 0;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// Sends the next int in potentialByte array
	// to the user as long as it still has values to send.  
	public int readBit(){
			// m
			if(counter < 8){
				int send = potentialByte[counter];
				counter++;
				return send;
			}else{
				putBitsInArray(potentialByte);
				counter = 0;
				int send = potentialByte[counter];
				counter++;
				return send;
			}
	}
	
	// PRE: file still has at least 1 byte left
	// Reads a byte from file and puts them into an array
	private void putBitsInArray(int[] ar) {
		try {
		
			byteRead = d.readUnsignedByte();
						
			for(int i = ar.length-1; i >= 0; i--){
				int bitRead = byteRead%2;
				potentialByte[i] = bitRead;
				byteRead = byteRead/2;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// returns the HuffmanTree
	public String getTree(){return tree;}
	// returns the total amount of chars in the original file
	public int totalChars(){return totalChars;}
	public void close(){
		try{
			d.close();
		}catch(IOException e){
			System.out.println("Data input didn't close");
		}	
	}
}
