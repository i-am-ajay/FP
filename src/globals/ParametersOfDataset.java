package globals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ParametersOfDataset {
	public static List<String> getParameters() throws IOException{
		List<String> parameterList = new ArrayList<>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL filePath = loader.getResource("output.text");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("files/data.dat")));
		String line = reader.readLine();
		String [] parameterArray = line.split(" ");
		for(String parameter : parameterArray) {
			parameter = parameter.replaceAll("'", "");
			parameterList.add(parameter.split("=")[0]);
		}
		return parameterList;
	}
	
	public static void main(String [] args) {
		try {
			ParametersOfDataset.getParameters();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
