package pkgStock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;
import java.util.logging.Level;
import java.util.HashMap;

public class GetPropertyValues {
	String result = "";
	InputStream inputStream;
	Utility logger = new Utility();
	
	public HashMap<String, String> getPropValues() throws IOException {
		HashMap<String, String> hm = new HashMap<>();
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// get the property value and print it out
			String dataFileBasePath = prop.getProperty("dataFileBasePath");
			String dataFileName = prop.getProperty("dataFileName");
			String dataFileTemplate = prop.getProperty("dataFileTemplate");
			String dataFileBackup = prop.getProperty("dataFileBackup");
			String stockBaseURL = prop.getProperty("stockBaseURL");
			String stockContextURL = prop.getProperty("stockContextURL");
			String stockMatchBaseURL = prop.getProperty("stockMatchBaseURL");
			String stockMatchContextURL = prop.getProperty("stockMatchContextURL");
			String btnStyle = prop.getProperty("btnStyle");
			String checkBoxStyle = prop.getProperty("checkBoxStyle");
			
			
			
			hm.put("dataFileBasePath", dataFileBasePath);
			hm.put("dataFileName", dataFileName);
			hm.put("dataFileTemplate", dataFileTemplate);
			hm.put("dataFileBackup", dataFileBackup);
			hm.put("stockBaseURL", stockBaseURL);
			hm.put("stockContextURL", stockContextURL);
			hm.put("stockMatchBaseURL", stockMatchBaseURL);
			hm.put("stockMatchContextURL", stockMatchContextURL);
			hm.put("btnStyle", btnStyle);
			hm.put("checkBoxStyle", checkBoxStyle);
			
			
		} catch (Exception exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		} finally {
			inputStream.close();
		}
		return hm;
	}

}
