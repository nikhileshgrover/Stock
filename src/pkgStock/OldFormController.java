package pkgStock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class OldFormController {
    @FXML private Text actionMessage;
    
    @FXML protected void handleSaveButtonAction(ActionEvent event) {
        actionMessage.setText("Save button pressed");
        
    }
    
    @FXML protected void handleRefreshButtonAction(ActionEvent event) {
        actionMessage.setText("Refresh button pressed");
        
    }
    
    @FXML protected void handleAddButtonAction(ActionEvent event) {
        actionMessage.setText("Add button pressed");
        
    }
    
    @FXML protected void handleRemoveButtonAction(ActionEvent event) {
        actionMessage.setText("Remove button pressed");
        
    }

}