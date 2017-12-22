package generatehash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ConvertToHash {
	public static String convertToHash(String string) throws NoSuchAlgorithmException{
		byte [] bytesOfMessage = string.getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		return new String(thedigest);
	}
	public static void main(String [] args){
		ConvertToHash hash = new ConvertToHash();
		try{
		
			System.out.println(hash.convertToHash("1234"));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
