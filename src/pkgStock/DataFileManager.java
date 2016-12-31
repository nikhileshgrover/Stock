package pkgStock;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.*;
import com.google.gson.*;

public class DataFileManager {

	File f;
	Utility logger = new Utility();
	
	public void initializeDataFile(HashMap<String, String> propertyConfigs) throws IOException {
		DataFileManager dfm = new DataFileManager();
	
		f = new File(propertyConfigs.get("dataFileBasePath").toString() + propertyConfigs.get("dataFileName").toString());
		
		if(!dfm.doesDataFileExists(f))
			dfm.createDataFile(propertyConfigs);
		else
			logger.log(Level.INFO, "File exists");
	}
	
	public Stocks getFileData(HashMap<String, String> propertyConfigs) throws IOException {
		Gson gson = new Gson();

		String localStockData = new String();
		localStockData = readFile(propertyConfigs.get("dataFileBasePath").toString() + propertyConfigs.get("dataFileName").toString(), Charset.defaultCharset());
		
		Stocks stockData = gson.fromJson(localStockData, Stocks.class);
		
	//	System.out.println(stockData.getStocks().get(0).getHighestPurchasePrice());
			
		return stockData;
	}
	
	public String prepareFileData(Stocks stockData) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String localStockData = new String();
		
		localStockData = gson.toJson(stockData);
		
		return localStockData;
	}
	
	public void saveFileData(String json, HashMap<String, String> propertyConfigs) {
		try {
			File dataFileBackup = new File(propertyConfigs.get("dataFileBasePath").toString() + propertyConfigs.get("dataFileBackup").toString());
			f.renameTo(dataFileBackup);

			FileWriter fw = new FileWriter(propertyConfigs.get("dataFileBasePath").toString() + propertyConfigs.get("dataFileName").toString());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
			
			restoreDataFile(propertyConfigs);
		}catch(Exception e){
			System.err.println("Error: " + e.getMessage());
			System.err.println(e);
		}
	}
	
	private void restoreDataFile(HashMap<String, String> propertyConfigs) {
//		propertyConfigs.get("dataFileBasePath").toString()
	}
	
	private String readFile(String path, Charset encoding) 
			  throws IOException {
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
	private boolean doesDataFileExists(File f) {
		if(f.exists() && !f.isDirectory())
			return true;
		return false;
	}
	
	private void createDataFile(HashMap<String, String> propertyConfigs) throws IOException {
		
		Writer writer = null;
		
		String content = new String(Files.readAllBytes(Paths.get(propertyConfigs.get("dataFileBasePath").toString() + propertyConfigs.get("dataFileTemplate").toString())));

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(propertyConfigs.get("dataFileBasePath").toString() + propertyConfigs.get("dataFileName").toString()), "utf-8"));
		    writer.write("[\n");
		    writer.write(content + "\n");
		    writer.write("]");
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}




