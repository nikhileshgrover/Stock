package pkgStock;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StockManager {
	private Stocks stockData;
	private HashMap<String, String> propertyConfigs;
	private Utility logger;
//	Constructor
	public StockManager(Stocks stockData, HashMap<String, String> propertyConfigs) {
		this.stockData = stockData;
		this.propertyConfigs = propertyConfigs;
		this.logger = new Utility();
	}
	
	public String saveFormData(List<HashMap<String, Object>> listOfFormFields) {
		String json = "";
		for(int i = 0;i < listOfFormFields.size(); i++) {
			String key = stockData.getStocks().get(i).getKey();
			
			CheckBox cbIsActive = (CheckBox) listOfFormFields.get(i).get(key + "cbIsActive");
			stockData.getStocks().get(i).setIsActive(cbIsActive.isSelected());
			
			TextField tfStockSymbol = (TextField) listOfFormFields.get(i).get(key + "tfStockSymbol");
			stockData.getStocks().get(i).setStockSymbol(tfStockSymbol.getText());
			
			TextField tfSector = (TextField) listOfFormFields.get(i).get(key + "tfSector");
			stockData.getStocks().get(i).setSector(tfSector.getText());
			
			Label lbStockName = (Label) listOfFormFields.get(i).get(key + "lbStockName");
			stockData.getStocks().get(i).setStockName(lbStockName.getText());
			
			TextField tfLowestPurchasePrice = (TextField) listOfFormFields.get(i).get(key + "tfLowestPurchasePrice");
			stockData.getStocks().get(i).setLowestPurchasePrice(Float.parseFloat(tfLowestPurchasePrice.getText()));
			
			TextField tfHighestPurchasePrice = (TextField) listOfFormFields.get(i).get(key + "tfHighestPurchasePrice");
			stockData.getStocks().get(i).setHighestPurchasePrice(Float.parseFloat(tfHighestPurchasePrice.getText()));
		
			TextField tfTargetPurchasePrice = (TextField) listOfFormFields.get(i).get(key + "tfTargetPurchasePrice");
			stockData.getStocks().get(i).setTargetPurchasePrice(Float.parseFloat(tfTargetPurchasePrice.getText()));	
			
			TextField tfLastTradedPrice = (TextField) listOfFormFields.get(i).get(key + "tfLastTradedPrice");
			stockData.getStocks().get(i).setLastTradedPrice(Float.parseFloat(tfLastTradedPrice.getText().replace(",", "")));
			
			TextField tfChange = (TextField) listOfFormFields.get(i).get(key + "tfChange");
			stockData.getStocks().get(i).setChange(Float.parseFloat(tfChange.getText()));
		
			TextField tfChangePercentage = (TextField) listOfFormFields.get(i).get(key + "tfChangePercentage");
			stockData.getStocks().get(i).setChangePercentage(Float.parseFloat(tfChangePercentage.getText()));
		}
		
		try {
		DataFileManager dm = new DataFileManager();
		dm.initializeDataFile(propertyConfigs);
		
		json = dm.prepareFileData(stockData);
		
		dm.saveFileData(json, propertyConfigs);
		
		}catch (IOException io){
			
		}
		return json;
	}
	
	public void setChangeFieldsColor(TextField tf, String newStrValue) {
		String color = "#008000";
		float newValue;
		newValue = Float.parseFloat(newStrValue);
		if(newValue < 0)
			color = "#FF0000";
			
		tf.setStyle("-fx-control-inner-background: " + color);		
	}
	
	public void setLastTradedPriceColor(TextField tf, String newStrValue) {
		String color = "#008000";
		float tfValue, newValue;
		tfValue = Float.parseFloat(tf.getText());
		newValue = Float.parseFloat(newStrValue);
		if(newValue < tfValue)
			color = "#FF0000";
		
		tf.setStyle("-fx-control-inner-background: " + color);		
	}
	
	public void setTargetPurchasePriceColor(TextField tf, String newStrValue) {
		String color = "#008000";
		float tfValue, newValue;
		tfValue = Float.parseFloat(tf.getText());
		newValue = Float.parseFloat(newStrValue);
		if(newValue > tfValue)
			color = "#FF0000";
		
		tf.setStyle("-fx-control-inner-background: " + color);		
	}
	
	
	
	public void refreshAllFromGoogle(List<HashMap<String, Object>> listOfFormFields) {
		for(int i = 0; i < listOfFormFields.size(); i++) {
			String key = stockData.getStocks().get(i).getKey();
			String stockSymbol = stockData.getStocks().get(i).getStockSymbol();
			
			List googleStockData = getGoogleStockAPIResponse(stockSymbol);
			
			TextField tfLastTradedPrice = (TextField) listOfFormFields.get(i).get(key + "tfLastTradedPrice");
			TextField tfChange = (TextField) listOfFormFields.get(i).get(key + "tfChange");
			TextField tfChangePercentage = (TextField) listOfFormFields.get(i).get(key + "tfChangePercentage");
			TextField tfTargetPurchasePrice = (TextField) listOfFormFields.get(i).get(key + "tfTargetPurchasePrice");
			
			GoogleStockData gsd =  (GoogleStockData) googleStockData.get(0);
			
			setTargetPurchasePriceColor(tfTargetPurchasePrice, gsd.getLastTradedPrice());
			
			setLastTradedPriceColor(tfLastTradedPrice, gsd.getLastTradedPrice());
			tfLastTradedPrice.setText(gsd.getLastTradedPrice());
			
			setChangeFieldsColor(tfChange, gsd.getChange());	
			tfChange.setText(gsd.getChange());
			
			setChangeFieldsColor(tfChangePercentage, gsd.getChangePercentage());
			tfChangePercentage.setText(gsd.getChangePercentage());
		}
		
	}
	
	private List getGoogleStockAPIResponse(String stockSymbol) {
		String stockResponse = "";
		
		try{
//			String uri = "http://finance.google.com/finance/info?client=ig&q=NASDAQ%3aAMZN";
			String uri = propertyConfigs.get("stockBaseURL").toString() + propertyConfigs.get("stockContextURL").toString() + URLEncoder.encode(stockSymbol, "UTF-8");
			logger.log(Level.INFO, uri);

			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "text/html");
			
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ connection.getResponseCode());
			}
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String s = "";
			do {
				stockResponse += s;
				s = buffer.readLine();
			} while (s != null);
			
			stockResponse = stockResponse.substring(2).trim();
			connection.disconnect();
			
		}catch (MalformedURLException mfe) {
//			mfe.printStackTrace();
			logger.log(Level.SEVERE, "Stock " + stockSymbol + " not found.");
//			LOGGER.severe("Stock " + stockSymbol + " not found.");
		}
		catch (RuntimeException | IOException exception) {
//			LOGGER.severe(exception.getMessage());
			logger.log(Level.SEVERE, exception.getMessage());
//			e.printStackTrace();
		}
		
		List googleStockData = googleStockAPIResponseToStockResponse(stockResponse);
		
		return googleStockData;
	}
	
	private List googleStockAPIResponseToStockResponse(String response) {

		Type collectionType = new TypeToken<Collection<GoogleStockData>>(){}.getType();
		List<GoogleStockData> googleStockData = (List<GoogleStockData>) new Gson()
	               .fromJson( response, collectionType);
		
		return googleStockData;
	}
	
	public Matches getStockMatch(String inputString) {
		String stockMatchResponse = "";
		Gson gson = new Gson();
		Matches matches = null;
		try {
			String uri = propertyConfigs.get("stockMatchBaseURL").toString() + propertyConfigs.get("stockMatchContextURL").toString() + URLEncoder.encode(inputString, "UTF-8");
//			LOGGER.info(uri);
			logger.log(Level.INFO, uri);
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ connection.getResponseCode());
			}
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String s = "";
			do {
				stockMatchResponse += s;
				s = buffer.readLine();
			} while (s != null);
			
//			stockMatchResponse = stockMatchResponse.substring(2).trim();
			
			matches = gson.fromJson(stockMatchResponse, Matches.class);
					
			connection.disconnect();
			
		} catch (IOException exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		}
		
		logger.log(Level.INFO, stockMatchResponse);
		return matches;
		
	}
	
}
