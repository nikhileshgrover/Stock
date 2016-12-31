package pkgStock;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

public class FormBuilder extends Application {
	
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * Scene scene
	 * 		Group root
	 * 			BorderPane borderPane
	 * 				TabPane tabPane
	 * 					Tab stocksTab
	 * 						BorderPane stockTabBorderPane
	 * 							GridPane gridPane
	 * 			
	 */

	private Stocks stockData;
	private HashMap<String, String> propertyConfigs = new HashMap<>();
	private List<HashMap<String, Object>> listOfFormFields = new ArrayList<HashMap<String, Object>>();
	private GetPropertyValues property = new GetPropertyValues();
	private StockManager stockManager;
	private GridPane gridPane = new GridPane();
	private Matches listOfMatches;
	private Utility logger;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		try {
//			Load the property file
			propertyConfigs = property.getPropValues();
			
//			Initialize the logger
			this.logger = new Utility();
			
//			Load data from stockData.json
			DataFileManager dm = new DataFileManager();
			dm.initializeDataFile(propertyConfigs);
			stockData = dm.getFileData(propertyConfigs);
			stockManager = new StockManager(stockData, propertyConfigs);
		}catch (IOException io) 
		{
			//create common logging library	
		}
		
		stage.setTitle("Stock Manager");
		Group root = new Group();
		Scene scene = new Scene(root, 1440, 900, Color.WHITE);
		TabPane tabPane = new TabPane();
		BorderPane borderPane = new BorderPane();
		BorderPane stockTabBorderPane = new BorderPane();
		final ScrollPane scrollPane = new ScrollPane();

		Tab stocksTab = new Tab("Stocks");
		
		stockTabBorderPane.setTop(this.addHBox());
		stockTabBorderPane.setCenter(scrollPane);
		scrollPane.setContent(this.createGridPane());
		stocksTab.setContent(stockTabBorderPane);
		tabPane.getTabs().add(stocksTab);
		
		borderPane.setCenter(tabPane);
	     
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
	      
	    root.getChildren().add(borderPane);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	
	private HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    Image btnImage;
	    ImageView imageView;
	    Label lbOutput = new Label();
	    lbOutput.setTextFill(Color.web("#FF0000"));

	    Button btnSave = new Button("_Save");
	    btnSave.setMnemonicParsing(true);
	    
	    btnSave.setPrefSize(150, 20);
	    btnSave.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override public void handle(ActionEvent e) {
	    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    		Date date = new Date();

	    		logger.log(Level.INFO, stockManager.saveFormData(listOfFormFields));
	    		lbOutput.setText("Saved: " + dateFormat.format(date)); 		
	        }
	    	
	    });
	    btnImage = new Image(getClass().getClassLoader().getResourceAsStream("images/SaveIcon.png"));
	    imageView = new ImageView(btnImage);
	    imageView.setFitHeight(29.0);
	    imageView.setFitWidth(30.0);
	    btnSave.setGraphic(imageView);
	    
//	    System.out.println(getClass().getResourceAsStream("/SaveIcon.png"));

	    Button btnAddStock = new Button("_Add");
	    btnAddStock.setMnemonicParsing(true);
	    btnAddStock.setPrefSize(150, 20);
	    btnAddStock.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override public void handle(ActionEvent e) {
	    		addStockFormField();
	    		lbOutput.setText("Form Field Added");
	        }
	    	
	    });
	    btnImage = new Image(getClass().getClassLoader().getResourceAsStream("images/PlusIcon.png"));
	    imageView = new ImageView(btnImage);
	    imageView.setFitHeight(29.0);
	    imageView.setFitWidth(30.0);
	    btnAddStock.setGraphic(imageView);
	 
	    Button btnRemoveStock = new Button("Remove");
	    btnRemoveStock.setPrefSize(150, 20);
	    btnRemoveStock.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override public void handle(ActionEvent e) {
	    		stockManager.getStockMatch("Microsoft");
	    		lbOutput.setText("Getting Match");
	        }
	    });
	    btnImage = new Image(getClass().getClassLoader().getResourceAsStream("images/MinusIcon.png"));
	    imageView = new ImageView(btnImage);
	    imageView.setFitHeight(29.0);
	    imageView.setFitWidth(30.0);
	    btnRemoveStock.setGraphic(imageView);
	   
	    Button btnRefreshStock = new Button("_Refresh");
	    btnRefreshStock.setMnemonicParsing(true);
	    btnRefreshStock.setPrefSize(150, 20);
	    btnRefreshStock.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override public void handle(ActionEvent e) {
	    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    		Date date = new Date();

	    		stockManager.refreshAllFromGoogle(listOfFormFields);
	    		logger.log(Level.INFO, stockManager.saveFormData(listOfFormFields));
	    		lbOutput.setText("Refreshed: " + dateFormat.format(date));
	        }
	    	
	    });
	    btnImage = new Image(getClass().getClassLoader().getResourceAsStream("images/RefreshIcon.png"));
	    imageView = new ImageView(btnImage);
	    imageView.setFitHeight(29.0);
	    imageView.setFitWidth(30.0);
	    btnRefreshStock.setGraphic(imageView);
	    
	    Label lbStream = new Label("Stream");
	    CheckBox cbStream = new CheckBox();
	    
	    
	    
	    ChangeListener runStockStream = new ChangeListener<Boolean>() {
	        @Override
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	            if (new_val){
	            	lbOutput.setText("Streaming...");
//	            	while(1==1)
	            		stockManager.refreshAllFromGoogle(listOfFormFields);
	            		logger.log(Level.INFO, stockManager.saveFormData(listOfFormFields));
	            }
	            else
	            	lbOutput.setText("");
	        }};
	        
	        cbStream.selectedProperty().addListener(runStockStream);
	    
	    hbox.getChildren().addAll(btnSave, btnAddStock, btnRefreshStock, lbStream, cbStream, lbOutput);
//	    System.out.println(stockData.getStocks().get(0).getSector());
	    return hbox;
	}
	
	private HashMap<String, Object> createGridRow(int v) {
		HashMap<String, Object> formFields = new HashMap<>();
		String key = stockData.getStocks().get(v).getKey();
		
		int textFieldWidth = 100;
		
		/*
		 * Array formFields
		 * 	HashMap formFields
		 * 		key
		 * 		cb
		 * 		...
		 * 		...
		 * 		...
		 */
		
		TextField tfSector = new TextField(stockData.getStocks().get(v).getSector());
		tfSector.setPrefWidth(textFieldWidth);
		
		CheckBox cbIsActive = new CheckBox();
		cbIsActive.setSelected(stockData.getStocks().get(v).getIsActive());
		
		Label lbStockName = new Label(stockData.getStocks().get(v).getStockName());
		TextField tfStockSymbol = new TextField(stockData.getStocks().get(v).getStockSymbol());
		tfStockSymbol.setPrefWidth(textFieldWidth);
		
		TextField tfLowestPurchasePrice = new TextField(Float.toString(stockData.getStocks().get(v).getLowestPurchasePrice()));
		tfLowestPurchasePrice.setPrefWidth(textFieldWidth);
		
		TextField tfHighestPurchasePrice = new TextField(Float.toString(stockData.getStocks().get(v).getHighestPurchasePrice()));
		tfHighestPurchasePrice.setPrefWidth(textFieldWidth);
		
		TextField tfTargetPurchasePrice = new TextField(Float.toString(stockData.getStocks().get(v).getTargetPurchasePrice()));
		tfTargetPurchasePrice.setPrefWidth(textFieldWidth);
		
		TextField tfLastTradedPrice = new TextField(Float.toString(stockData.getStocks().get(v).getLastTradedPrice()));
		tfLastTradedPrice.setPrefWidth(textFieldWidth);
		
		TextField tfChangePercentage = new TextField(Float.toString(stockData.getStocks().get(v).getChangePercentage()));
		tfChangePercentage.setPrefWidth(textFieldWidth);
		
		TextField tfChange = new TextField(Float.toString(stockData.getStocks().get(v).getChange()));
		tfChange.setPrefWidth(textFieldWidth);
		
		formFields.put(key + "cbIsActive", cbIsActive);
		formFields.put(key + "tfSector", tfSector);
		formFields.put(key + "lbStockName", lbStockName);
		formFields.put(key + "tfStockSymbol", tfStockSymbol);
		formFields.put(key + "tfLowestPurchasePrice", tfLowestPurchasePrice);
		formFields.put(key + "tfHighestPurchasePrice", tfHighestPurchasePrice);
		formFields.put(key + "tfTargetPurchasePrice", tfTargetPurchasePrice);
		formFields.put(key + "tfLastTradedPrice", tfLastTradedPrice);
		formFields.put(key + "tfChangePercentage", tfChangePercentage);
		formFields.put(key + "tfChange", tfChange);
		
		return formFields;
	}
	
	private GridPane createGridPane() {
		
		
//		gridPane.setGridLinesVisible(true);
		gridPane.setLayoutX(39);
		gridPane.setLayoutY(131.0);
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setVgap(5.0);
		gridPane.setHgap(10.0);
	
		gridPane.add(new Label("Active"), 1,1);
		gridPane.add(new Label("Stock"), 2, 1);
		gridPane.add(new Label("Symbol"), 3, 1);
		gridPane.add(new Label("Sector"), 4, 1);
		gridPane.add(new Label("LPP"), 5, 1);
		gridPane.add(new Label("HPP"), 6, 1);
		gridPane.add(new Label("TP"), 7, 1);
		gridPane.add(new Label("LTP"), 8, 1);
		gridPane.add(new Label("Change %"), 9, 1);
		gridPane.add(new Label("Change"), 10, 1);
		
		for(int v=0;v < this.stockData.getStocks().size(); v++) {
			addRowToGridPane(v);
		}
		
//		System.out.println(listOfFormFields.get(0).get("amazoncbIsActive"));
		
		return gridPane;
	}
	
	private Matches cleanListOfMatches() {
		Matches lom = new Matches();
		List<Match> m = new ArrayList<Match>();
		for(int i=0; i < listOfMatches.getMatches().size(); i++){
			if(listOfMatches.getMatches().get(i).getExchange().length() > 0 && listOfMatches.getMatches().get(i).getTicker().length() > 0) {
				m.add(listOfMatches.getMatches().get(i));
			}
		}
		lom.setMatches(m);
		return lom;
	}
	
	private void addRowToGridPane(int v) {
		HashMap<String, Object> formFields;
		String key = stockData.getStocks().get(v).getKey();
		formFields = createGridRow(v);
		listOfFormFields.add(formFields);
		
		gridPane.add((CheckBox) formFields.get(key + "cbIsActive"), 1, v+2); //CheckBox Active
		gridPane.add((Label) formFields.get(key + "lbStockName"), 2, v+2); //Label StockName
		gridPane.add((TextField) formFields.get(key + "tfStockSymbol"), 3,v+2); //TextField StockSymbol
		gridPane.add((TextField) formFields.get(key + "tfSector"), 4,v+2); //TextField StockSymbol
		gridPane.add((TextField) formFields.get(key + "tfLowestPurchasePrice"), 5,v+2);
		gridPane.add((TextField) formFields.get(key + "tfHighestPurchasePrice"), 6,v+2);
		gridPane.add((TextField) formFields.get(key + "tfTargetPurchasePrice"), 7,v+2);
		gridPane.add((TextField) formFields.get(key + "tfLastTradedPrice"), 8,v+2);
		gridPane.add((TextField) formFields.get(key + "tfChangePercentage"), 9,v+2);
		gridPane.add((TextField) formFields.get(key + "tfChange"), 10,v+2);
	}
	
	private void addStockFormField() {
		Dialog<HashMap> newStockDialog = new Dialog();
		newStockDialog.setTitle("Add a new Stock");
		newStockDialog.setHeaderText("Add a new Stock.");
		newStockDialog.setResizable(true);
		
		Label lbKey = new Label("Key");
		TextField tfKey = new TextField();
		
		Label lbStockName = new Label("Stock");
		TextField tfStockName = new TextField();
		
		Label lbStockSymbol = new Label("Stock Symbol");
		ComboBox<String> cbStockSymbol = new ComboBox<String>();
		cbStockSymbol.setEditable(true);
		
		cbStockSymbol.setOnKeyReleased(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
//	        	System.out.println(ke.getCode().isLetterKey() + " | " + ke.getCode().isDigitKey());
//	        	if(false){
	        	if(ke.getCode().isLetterKey() || ke.getCode().isDigitKey() || ke.getCode().equals(KeyCode.BACK_SPACE) || ke.getCode().equals(KeyCode.DELETE)) {
	        		listOfMatches = stockManager.getStockMatch(cbStockSymbol.getEditor().getText());
	        		listOfMatches = cleanListOfMatches();
	        		
	        		cbStockSymbol.getItems().remove(0, cbStockSymbol.getItems().size());
		        		
	        		for(int i=0; i < listOfMatches.getMatches().size(); i++){
//        				cbStockSymbol.getItems().add(
//        						i
//        						+ " | " + listOfMatches.getMatches().get(i).getId()
//        						+ " | " + listOfMatches.getMatches().get(i).getExchange()
//        						+ ":" + listOfMatches.getMatches().get(i).getTicker()
//        						);
	        			cbStockSymbol.getItems().add(
        						listOfMatches.getMatches().get(i).getExchange()
        						+ ":" + listOfMatches.getMatches().get(i).getTicker()
        						);
	        		}
	        		cbStockSymbol.show();
	        	}
	        }
	    });
		
		cbStockSymbol.valueProperty().addListener(new ChangeListener<String>() {
	            @Override 
	            public void changed(ObservableValue ov, String t, String t1) {
	                tfKey.setText(listOfMatches.getMatches().get(cbStockSymbol.getSelectionModel().getSelectedIndex()).getId());
	                tfStockName.setText(listOfMatches.getMatches().get(cbStockSymbol.getSelectionModel().getSelectedIndex()).getName());
//	                System.out.println(
//	                		cbStockSymbol.getSelectionModel().getSelectedIndex()
//	                		+ " | " + listOfMatches.getMatches().get(cbStockSymbol.getSelectionModel().getSelectedIndex()).getName() + " | "
//	                		+ listOfMatches.getMatches().get(cbStockSymbol.getSelectionModel().getSelectedIndex()).getExchange() + " | "
//	                		+ listOfMatches.getMatches().get(cbStockSymbol.getSelectionModel().getSelectedIndex()).getTicker() + " | "
//	                		+listOfMatches.getMatches().get(cbStockSymbol.getSelectionModel().getSelectedIndex()).getId()
//	                		);
	            }    
	        });
		
		Label lbStockSector = new Label("Sector");
		TextField tfStockSector = new TextField();
		
		GridPane addStockgridPane = new GridPane();
		addStockgridPane.setVgap(5.0);
		addStockgridPane.setHgap(10.0);
		
		addStockgridPane.add(lbKey, 1,1);
		addStockgridPane.add(tfKey, 2,1);
		
		addStockgridPane.add(lbStockName, 1,2);
		addStockgridPane.add(tfStockName, 2,2);
		
		addStockgridPane.add(lbStockSymbol, 1,3);
		addStockgridPane.add(cbStockSymbol, 2, 3);
		
		addStockgridPane.add(lbStockSector, 1,4);
		addStockgridPane.add(tfStockSector, 2,4);
		
		newStockDialog.getDialogPane().setContent(addStockgridPane);
		
		ButtonType buttonTypeAdd = new ButtonType("Add", ButtonData.OK_DONE);
		ButtonType btypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType btypeClear = new ButtonType("Clear", ButtonData.BACK_PREVIOUS);
		
		newStockDialog.getDialogPane().getButtonTypes().add(buttonTypeAdd);
		newStockDialog.getDialogPane().getButtonTypes().add(btypeCancel);
		newStockDialog.getDialogPane().getButtonTypes().add(btypeClear);
				
		newStockDialog.setResultConverter(new Callback<ButtonType, HashMap>() {
		    @Override
		    public HashMap call(ButtonType b) {

		        if (b == buttonTypeAdd) {
		        	HashMap<String, String> stockMap = new HashMap<>();
		        	
		        	stockMap.put("key", tfKey.getText());
		        	stockMap.put("stockName", tfStockName.getText());
		        	stockMap.put("stockSymbol", cbStockSymbol.getEditor().getText());
		        	stockMap.put("stockSector", tfStockSector.getText());

		            return stockMap;
//		        	return new Result(tfKey.getText(), tfStockName.getText(), tfStockSymbol.getText());
		        }

		        return null;
		    }
		});
				
		Optional<HashMap> result = newStockDialog.showAndWait();
		
		if (result.isPresent()) {
			HashMap<String, String> hmResult = new HashMap<>();
			hmResult = result.get();
			Stock stock = new Stock();
			
			stock.setKey(hmResult.get("key"));
			stock.setStockName(hmResult.get("stockName"));
			stock.setStockSymbol(hmResult.get("stockSymbol"));
			stock.setSector(hmResult.get("stockSector"));
			
//			this is the segment where i need to add an if condition to check if hmResult.get("stockSymbol") exists in the stockData.getStocks() list. 
//			If it does not exist, then add, else message "Stock already exists"
			if(checkForDuplicateStock(hmResult.get("stockSymbol"))) {
				stockData.getStocks().add(stock);
				addRowToGridPane(stockData.getStocks().size()-1);
				stockManager.refreshAllFromGoogle(listOfFormFields);
				
				try {
					DataFileManager dm = new DataFileManager();
					dm.initializeDataFile(propertyConfigs);
					
					String json = dm.prepareFileData(stockData);
					
					dm.saveFileData(json, propertyConfigs);
					
					}catch (IOException io){
				}
			}
			else {
//				display duplicate stock message
			}
		}
	}
	
	public boolean checkForDuplicateStock(String stockSymbol) {
		for(int i=0; i < stockData.getStocks().size();i++) {
			if(stockData.getStocks().get(i).getStockSymbol().equals(stockSymbol)) {
				return false;
			}
		}
		return true;
	}
}