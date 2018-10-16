import java.io.*;
public abstract class BitOutputStream {

	protected DataOutputStream d;
	public BitOutputStream(String filename){
		try{
			d = new DataOutputStream(new FileOutputStream(filename));
		}catch(IOException e){

		}
	}
	public abstract void close();
	public abstract void writeBit(int bit);
}
