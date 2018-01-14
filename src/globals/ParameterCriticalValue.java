package globals;

import java.util.HashMap;
import java.util.Map;

import AddEntriesToDB.ParametersToDB;

public class ParameterCriticalValue {
	static Map<String,Double> criticalValueMap;
	static{
		if(criticalValueMap == null){
			//criticalValueMap = new HashMap<>();
			//setCriticalValueMap();
			
			criticalValueMap = ParametersToDB.getCriticalValueMap();
		}
		
	}
	private static void setCriticalValueMap(){
		criticalValueMap.put("age", 50.0); // 18 - 95
		criticalValueMap.put("job", 0.7);
		criticalValueMap.put("marital", 0.0); // divorced, married, single
		criticalValueMap.put("education", 0.75); // primary,secondary, tertiary, unknown
		criticalValueMap.put("default", 0.0); 
		criticalValueMap.put("balance", 55000.0);// -10k - 102k
		criticalValueMap.put("housing", 0.0); // yes - no
		criticalValueMap.put("loan", 0.0); // yes - no
		criticalValueMap.put("contact", 0.8); // cellular, telephone, unknown
		criticalValueMap.put("day", 15.0); // 0-31
		criticalValueMap.put("month", 0.0); 
		criticalValueMap.put("duration", 1500.0); // 0 - 4900
		criticalValueMap.put("campaign", 50.0); // 1 - 63
		criticalValueMap.put("pdays", 300.0); // -1 - 871
		criticalValueMap.put("previous", 170.0); // 0 - 275
		criticalValueMap.put("poutcome", 0.8); // failure success unknown other
		criticalValueMap.put("y", 0.0);
		
	}
	public static Double getCriticalValueMap(String key){
		return criticalValueMap.get(key);
	}
	/*final static public int AGE = 50;
	final static public int JOB = 0;
	final static public int MARITAL = 0;
	final static public int EDUCATION = 0;
	final static public int DEFAULT = 0;
	final static public int BALANCE = 0;
	final static public int HOUSING = 0;
	final static public int LOAN = 0;
	final static public int CONTACT = 0;
	final static public int DAY = 0;
	final static public int MONTH = 0;
	final static public int DURATION = 0;
	final static public int CAMPAIGN = 0;
	final static public int PDAYS = 0;
	final static public int PREVIOUS = 0;
	final static public int POUTCOME = 0;
	final static public int Y = 0;*/
}
