package pkgStock;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GoogleStockData {
//	ID
	@SerializedName("id")
	@Expose
	private String id;
	
//	ticker
	@SerializedName("t")
	@Expose
	private String ticker;
	
//	Exchange
	@SerializedName("e")
	@Expose
	private String exchange;
	
//	LastTradePrice
	@SerializedName("l")
	@Expose
	private String lastTradedPrice;
	
	@SerializedName("l_fix")
	@Expose
	private String lastTradedPriceFix;
	
//	LastTradeWithCurrency
	@SerializedName("l_cur")
	@Expose
	private String lastTradeWithCurrency;
	
//	LastTradeSize
	@SerializedName("s")
	@Expose
	private String lastTradeSize;
	
//	Last Traded Time
	@SerializedName("ltt")
	@Expose
	private String lastTradedTime;
	
//	LastTradeDateTimeLong
	@SerializedName("lt")
	@Expose
	private String lastTradeDateTimeLong;
	
//	lastTradedDateTimeStamp
	@SerializedName("lt_dts")
	@Expose
	private String lastTradedDateTimeStamp;
	
//	Change
	@SerializedName("c")
	@Expose
	private String change;
	
	@SerializedName("c_fix")
	@Expose
	private String changeFix;
	
//	Change Percentage
	@SerializedName("cp")
	@Expose
	private String changePercentage;
	
	@SerializedName("cp_fix")
	@Expose
	private String changePercentageFix;
	
	
	@SerializedName("ccol")
	@Expose
	private String ccol;
	
//	PreviousClosePrice
	@SerializedName("pcls_fix")
	@Expose
	private String previousClosePriceFix;

	/**
	 * 
	 * @return The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The t
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * 
	 * @param t
	 *            The t
	 */
	public void setTicker(String t) {
		this.ticker = t;
	}

	/**
	 * 
	 * @return The e
	 */
	public String getExchange() {
		return exchange;
	}

	/**
	 * 
	 * @param e
	 *            The e
	 */
	public void setExchange(String e) {
		this.exchange = e;
	}

	/**
	 * 
	 * @return The l
	 */
	public String getLastTradedPrice() {
		return lastTradedPrice.replace(",", "");
	}

	/**
	 * 
	 * @param l
	 *            The l
	 */
	public void setLastTradedPrice(String l) {
		this.lastTradedPrice = l;
	}

	/**
	 * 
	 * @return The last traded priceFix
	 */
	public String getLastTradedPriceFix() {
		return lastTradedPriceFix;
	}

	/**
	 * 
	 * @param lFix
	 *            The l_fix
	 */
	public void setLastTradedPriceFix(String lFix) {
		this.lastTradedPriceFix = lFix;
	}

	/**
	 * 
	 * @return The lCur
	 */
	public String getLastTradeWithCurrency() {
		return lastTradeWithCurrency;
	}

	/**
	 * 
	 * @param lCur
	 *            The l_cur
	 */
	public void setLastTradeWithCurrency(String lCur) {
		this.lastTradeWithCurrency = lCur;
	}

	/**
	 * 
	 * @return The s
	 */
	public String getLastTradeSize() {
		return lastTradeSize;
	}

	/**
	 * 
	 * @param s
	 *            The s
	 */
	public void setLastTradeSize(String s) {
		this.lastTradeSize = s;
	}

	/**
	 * 
	 * @return The ltt
	 */
	public String getLastTradedTime() {
		return lastTradedTime;
	}

	/**
	 * 
	 * @param ltt
	 *            The ltt
	 */
	public void setLastTradedTime(String ltt) {
		this.lastTradedTime = ltt;
	}

	/**
	 * 
	 * @return The lt
	 */
	public String getLastTradeDateTimeLong() {
		return lastTradeDateTimeLong;
	}

	/**
	 * 
	 * @param lt
	 *            The lt
	 */
	public void setLastTradeDateTimeLong(String lt) {
		this.lastTradeDateTimeLong = lt;
	}

	/**
	 * 
	 * @return The ltDts
	 */
	public String getLastTradedDateTimeStamps() {
		return lastTradedDateTimeStamp;
	}

	/**
	 * 
	 * @param ltDts
	 *            The lt_dts
	 */
	public void setLastTradedDateTimeStamp(String ltDts) {
		this.lastTradedDateTimeStamp = ltDts;
	}

	/**
	 * 
	 * @return The c
	 */
	public String getChange() {
		return change;
	}

	/**
	 * 
	 * @param c
	 *            The c
	 */
	public void setChange(String c) {
		this.change = c;
	}

	/**
	 * 
	 * @return The cFix
	 */
	public String getChangeFix() {
		return changeFix;
	}

	/**
	 * 
	 * @param cFix
	 *            The c_fix
	 */
	public void setChangeFix(String cFix) {
		this.changeFix = cFix;
	}

	/**
	 * 
	 * @return The cp
	 */
	public String getChangePercentage() {
		return changePercentage;
	}

	/**
	 * 
	 * @param cp
	 *            The cp
	 */
	public void setChangePercentage(String cp) {
		this.changePercentage = cp;
	}

	/**
	 * 
	 * @return The cpFix
	 */
	public String getChangePercentageFix() {
		return changePercentageFix;
	}

	/**
	 * 
	 * @param cpFix
	 *            The cp_fix
	 */
	public void setChangePercentageFix(String cpFix) {
		this.changePercentageFix = cpFix;
	}

	/**
	 * 
	 * @return The ccol
	 */
	public String getCcol() {
		return ccol;
	}

	/**
	 * 
	 * @param ccol
	 *            The ccol
	 */
	public void setCcol(String ccol) {
		this.ccol = ccol;
	}

	/**
	 * 
	 * @return The pclsFix
	 */
	public String getPreviousClosePriceFix() {
		return previousClosePriceFix;
	}

	/**
	 * 
	 * @param pclsFix
	 *            The pcls_fix
	 */
	public void setPreviousClosePriceFix(String pclsFix) {
		this.previousClosePriceFix = pclsFix;
	}
}