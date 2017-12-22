package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ResourceTest {
	public static void main(String [] args) throws FileNotFoundException{
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		//ClassLoader loader = ResourceTest.class.getClassLoader();
		URL url = loader.getResource("b2.jpg");
		//URL url = loader.getResource("b2.jpg");
		System.out.println(url);
		/*try{
			Path path = Paths.get("/images/b2.jpg");
			FileInputStream stream = new FileInputStream("images/b2.jpg");
			System.out.println(stream);
			int i = 0;
			while((i=stream.read()) != -1){
				System.out.print(i);
			
			
			}
			//System.out.println(path.toAbsolutePath());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}*/
		//URL resourcePath = ResourceTest.class.getResource("b2.jpg");
		//System.out.println(resourcePath.toString());
		System.out.println(System.getenv("classpath"));
	}
}
