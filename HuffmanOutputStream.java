import java.io.*;
// Walker Christensen
public class HuffmanOutputStream extends BitOutputStream{
	private int positionInBit;
	private int b;
	public HuffmanOutputStream(String filename, String tree, int totalChars){
		super(filename);
		try{
			d.writeUTF(tree);
			d.writeInt(totalChars);
			positionInBit = 0;
			b = 0;
		}catch(IOException e){}
	}
	public void writeBit(int bit){
		//PRE bit == 0 || bit == 1
		// Makes the byte that will be sent to file bit by bit
		if(positionInBit < 8){
			positionInBit++;
			if(bit == 1){
				b = b*2+bit;
			}else{
				b = b*2;
			}
		}else{
			try{
				// writes byte representation of the encodings to file
				d.writeByte(b);
				b = bit;
				positionInBit = 1;
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public void close(){
		try{
			// Will make the file have a whole number of bytes
			while(positionInBit < 8){
				b = b*2;
				positionInBit++;
			}
			d.writeByte(b);
			d.close();
		}catch(IOException e){
			System.out.println("close didn't work");
		}
	}
}

