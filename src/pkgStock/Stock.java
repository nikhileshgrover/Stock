package pkgStock;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Stock {

	@SerializedName("key")
	@Expose
	private String key;
	
	@SerializedName("sector")
	@Expose
	private String sector;
	
	@SerializedName("isActive")
	@Expose
	private Boolean isActive = true;
	
	@SerializedName("stockName")
	@Expose
	private String stockName;
	
	@SerializedName("stockSymbol")
	@Expose
	private String stockSymbol;
	
	@SerializedName("lowestPurchasePrice")
	@Expose
	private float lowestPurchasePrice;
	
	@SerializedName("highestPurchasePrice")
	@Expose
	private float highestPurchasePrice;
	
	@SerializedName("targetPurchasePrice")
	@Expose
	private float targetPurchasePrice;
	
	@SerializedName("lastTradedPrice")
	@Expose
	private float lastTradedPrice;
	
	@SerializedName("changePercentage")
	@Expose
	private float changePercentage;
	
	@SerializedName("change")
	@Expose
	private float change;
	
	
	

	/**
	 * 
	 * @return
	 * The key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 * The key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * @return
	 * The sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * 
	 * @param sector
	 * The sector
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * 
	 * @return
	 * The isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * 
	 * @param isActive
	 * The isActive
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * 
	 * @return
	 * The stockName
	 */
	public String getStockName() {
		return stockName;
	}
	
	/**
	 * 
	 * @param stockName
	 * The stockName
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	
	/**
	 * 
	 * @return
	 * The stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * 
	 * @param stockSymbol
	 * The stockSymbol
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	
	/**
	 * 
	 * @return
	 * The lowestPurchasePrice
	 */
	public float getLowestPurchasePrice() {
		return lowestPurchasePrice;
	}
	
	/**
	 * 
	 * @param lowestPurchasePrice
	 * The lowestPurchasePrice
	 */
	public void setLowestPurchasePrice(float lowestPurchasePrice) {
		this.lowestPurchasePrice = lowestPurchasePrice;
	}

	/**
	 * 
	 * @return
	 * The highestPurchasePrice
	 */
	public float getHighestPurchasePrice() {
		return highestPurchasePrice;
	}
	
	/**
	 * 
	 * @param highestPurchasePrice
	 * The highestPurchasePrice
	 */
	public void setHighestPurchasePrice(float highestPurchasePrice) {
		this.highestPurchasePrice = highestPurchasePrice;
	}
	
	/**
	 * 
	 * @return
	 * The targetPurchasePrice
	 */
	public float getTargetPurchasePrice() {
		return targetPurchasePrice;
	}
	
	/**
	 * 
	 * @param targetPurchasePrice
	 * The targetPurchasePrice
	 */
	public void setTargetPurchasePrice(float targetPurchasePrice) {
		this.targetPurchasePrice = targetPurchasePrice;
	}
	
	/**
	 * 
	 * @return
	 * The lastTradedPrice
	 */
	public float getLastTradedPrice() {
		return lastTradedPrice;
	}
	
	/**
	 * 
	 * @param lastTradedPrice
	 * The lastTradedPrice
	 */
	public void setLastTradedPrice(float lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
	}
	
	/**
	 * 
	 * @return
	 * The changePercentage
	 */
	public float getChangePercentage() {
		return changePercentage;
	}
	
	/**
	 * 
	 * @param changePercentage
	 * The changePercentage
	 */
	public void setChangePercentage(float changePercentage) {
		this.changePercentage = changePercentage;
	}
	
	/**
	 * 
	 * @return
	 * The change
	 */
	public float getChange() {
		return change;
	}
	
	/**
	 * 
	 * @param change
	 * The change
	 */
	public void setChange(float change) {
		this.change = change;
	}
}