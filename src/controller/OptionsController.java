package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.account.Account;
import view.menu.OptionsView;
import utilities.StringUtils;
/**
 * 
 * This class controls the Options view.
 *
 */
public class OptionsController implements Initializable {

    private static final String BACK_KEY = "back";
    private static final String FIRST_CHOICE_CB1 = "720x480";
    private static final String SECOND_CHOICE_CB1 = "1024x600";
    private static final String THIRD_CHOICE_CB1 = "1280x720";
    private static final String FOURTH_CHOICE_CB1 = "1920×1080";
    private static final String FIRST_CHOICE_CB2 = "ITA";
    private static final String SECOND_CHOICE_CB2 = "ENG";
    private static final String THIRD_CHOICE_CB2 = "RUS";
    private static final String RESOLUTION_KEY = "resolution";
    private static final String LANGUAGE_KEY = "language";
    private static final String SOUND_KEY = "sound";
    private static final String YES_KEY = "yes";
    private static final String NO_KEY = "no";
    private static final String CHANGE_SHIP_KEY = "change_ship";
    private final Account account;
    private final OptionsView view;
    private ResourceBundle bundle;
    private final ObservableList<String> resolutionlist = FXCollections.observableArrayList(FIRST_CHOICE_CB1, SECOND_CHOICE_CB1, THIRD_CHOICE_CB1, FOURTH_CHOICE_CB1);
    private final ObservableList<String> languagelist = FXCollections.observableArrayList(FIRST_CHOICE_CB2, SECOND_CHOICE_CB2, THIRD_CHOICE_CB2);
    @FXML
    private Button back;
    @FXML
    private ChoiceBox<String> language; 
    @FXML
    private ChoiceBox<String> resolution;
    @FXML
    private RadioButton yesRb;
    @FXML
    private RadioButton noRb;
    @FXML
    private Label resolutionLb;
    @FXML
    private Label languageLb;
    @FXML
    private Label soundLb;
    @FXML
    private Label changeShipLb;
    @FXML
    private ToggleGroup choice;

    /**
     * 
     * @param account 
     */
    public OptionsController(final Account account) {
        this.account = account;
        this.view = new OptionsView(account.getSettings().getLanguage(), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.bundle = resources;
        setLanguage();
        this.language.setValue(this.account.getSettings().getLanguage());
        this.language.setItems(languagelist);
        this.resolution.setItems(resolutionlist);
    }

    /**
     * Method to set the language.
     */
    private void setLanguage() {
        this.back.setText(bundle.getString(BACK_KEY));
        this.resolutionLb.setText(bundle.getString(RESOLUTION_KEY));
        this.languageLb.setText(bundle.getString(LANGUAGE_KEY));
        this.soundLb.setText(bundle.getString(SOUND_KEY));
        this.yesRb.setText(bundle.getString(YES_KEY));
        this.noRb.setText(bundle.getString(NO_KEY));
        this.changeShipLb.setText(bundle.getString(CHANGE_SHIP_KEY));
    }

    /**
     * Method to go back to the menu.
     */
    public void goBack() {
        final Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
        new MenuController(account).start();
    }

    /**
     * Method to set radio bunttons.
     */
    public void radioBunttonChanged() {
    }

    /**
     * Start method to load the view.
     */
    public void start() {
        try {
            this.view.start(new Stage());
        } catch (Exception e) {
            System.out.println(StringUtils.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}
