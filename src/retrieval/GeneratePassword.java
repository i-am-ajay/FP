package retrieval;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratePassword {
	static List<Character> charset; 
	static{
		charset = new ArrayList<Character>();
		for(int i=48; i<58;i++){
			charset.add((char)i);
		}
		for(int i=65; i<91;i++){
			charset.add((char)i);
		}
		for(int i=97; i<123;i++){
			charset.add((char)i);
		}
	}
	public String generatePassowrd(){
		Random rand = new Random();
		
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<6; i++){
			int index = rand.nextInt(51);
			builder.append(charset.get(index));
		}
		return builder.toString();
	}
	public static void main(String [] args){
		GeneratePassword pass = new GeneratePassword();
		System.out.println(pass.generatePassowrd());
	}
}
