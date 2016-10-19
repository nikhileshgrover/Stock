package pkgStock;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Stocks {

	@SerializedName("stocks")
	@Expose
	private List<Stock> stocks = new ArrayList<Stock>();

	/**
	 * 
	 * @return
	 * The stocks
	 */
	public List<Stock> getStocks() {
		return stocks;
	}

	/**
	 * 
	 * @param stocks
	 * The stocks
	 */
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

}