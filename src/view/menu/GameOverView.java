package view.menu;

import java.io.IOException;
import java.util.ResourceBundle;
import controller.StageController;
import controller.menu.GameOverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import model.account.Account;
import utilities.BundleUtils;
import utilities.ErrorLog;

/**
 * 
 * view of the Game over window.
 *
 */
public class GameOverView {

    private static final String PAUSE_VIEW = "gameOverView.fxml";
    private static final String PAUSE_BUNDLE = "game.GameOverBundle";
    private static final double WIDTH = 2.4;
    private static final double HEIGHT = 2.07;
    private SubScene subScene;
    /**
     * 
     * @param account account
     * @param stageController stageController
     * @param gameOverController the controller of this class
     */
    public GameOverView(final Account account, final StageController stageController, final GameOverController gameOverController) {
        BundleUtils.setLocale(account.getSettings().getLanguage());
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(PAUSE_VIEW), ResourceBundle.getBundle(PAUSE_BUNDLE));
        loader.setController(gameOverController);
        try {
            this.subScene = new SubScene(loader.load(), stageController.getScene().getWidth() / WIDTH, stageController.getScene().getHeight() / HEIGHT);
        } catch (IOException e) {
            ErrorLog.getLog().printError();
            System.exit(0);
        }
    }
    /**
     * 
     * @return the subScene.
     */
    public SubScene getSubScene() {
        return this.subScene;
    }
}
