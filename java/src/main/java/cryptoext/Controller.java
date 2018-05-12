package cryptoext;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class Controller {
    @FXML
    private TextArea publicKeyText;
    @FXML
    private TextArea inText;
    @FXML
    private TextArea outText;

    public void handleCryptButtonAction() {
        outText.setText(new cryptoext.CryptoService(publicKeyText.getText()).encrypt(inText.getText()));
    }

}
