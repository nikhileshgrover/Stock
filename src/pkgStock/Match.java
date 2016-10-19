package pkgStock;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Match {

	@SerializedName("t")
	@Expose
	private String ticker;
	@SerializedName("n")
	@Expose
	private String name;
	@SerializedName("e")
	@Expose
	private String exchange;
	@SerializedName("id")
	@Expose
	private String id;

	/**
	 * 
	 * @return
	 * The t
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * 
	 * @param t
	 * The t
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * 
	 * @return
	 * The n
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param n
	 * The n
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 * The e
	 */
	public String getExchange() {
		return exchange;
	}

	/**
	 * 
	 * @param e
	 * The e
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	/**
	 * 
	 * @return
	 * The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 * The id
	 */
	public void setId(String id) {
		this.id = id;
	}

}