import config.ColorScheme;
import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.*;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import game.scenes.BaseScene;
import game.scenes.DemoScene;

/**
 * Auf Basis dieses Grundgerüst können Sie Ihr eigenes Spiel entwickeln. In der GameConfig finden Sie
 * für den Fall der Fälle eine Sammlung weihnachtlicher Farbwerte. Bitte füllen Sie vor der Abgabe Ihres
 * Beitrags die Readme-Datei in diesem Projektverzeichnis aus und laden Sie erst dann das gesamte Projekt
 * als ZIP-Datei hoch.
 */

public class NightOfTheLivingBread extends GraphicsApp {

    private BaseScene currentScene;
    private DemoScene demoScene;

    @Override
    public void initialize() {
        setCanvasSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        demoScene = new DemoScene(this, "DemoScene", ColorScheme.DARK_SEA_GREEN);
        currentScene = demoScene;
    }

    @Override
    public void draw() {
        currentScene.render();
    }

    @Override
    public void onMousePressed(MousePressedEvent event) {
       currentScene.handleMousePressed(event);
    }

    @Override
    public void onMouseReleased(MouseReleasedEvent event) {
        currentScene.handleMouseReleased(event);
    }

    @Override
    public void onMouseMoved(MouseMovedEvent event) {
        currentScene.handleMouseMoved(event);
    }

    @Override
    public void onMouseDragged(MouseDraggedEvent event) {
        currentScene.handleMouseDragged(event);
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        currentScene.handleKeyPressed(event);
    }

    @Override
    public void onKeyReleased(KeyReleasedEvent event) {
        currentScene.handleKeyReleased(event);
    }
    
    public static void main(String[] args) {
        // Instanziiert eine Instanz dieser Klasse und startet die GraphicsApp
        GraphicsAppLauncher.launch();
    }
}
