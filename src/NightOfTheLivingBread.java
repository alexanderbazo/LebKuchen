import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.*;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import game.scenes.BaseScene;
import game.scenes.IntroScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;
import utils.FontLoader;

import java.util.WeakHashMap;

/**
 * Auf Basis dieses Grundgerüst können Sie Ihr eigenes Spiel entwickeln. In der GameConfig finden Sie
 * für den Fall der Fälle eine Sammlung weihnachtlicher Farbwerte. Bitte füllen Sie vor der Abgabe Ihres
 * Beitrags die Readme-Datei in diesem Projektverzeichnis aus und laden Sie erst dann das gesamte Projekt
 * als ZIP-Datei hoch.
 */

public class NightOfTheLivingBread extends GraphicsApp implements SceneListener {

    private BaseScene currentScene;
    private WeakHashMap<String, BaseScene> scenes;

    @Override
    public void initialize() {
        FontLoader.loadFonts(GameConfig.FONT_DIR);
        setCanvasSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        scenes = new WeakHashMap<>();
        scenes.put(IntroScene.SCENE_NAME, new IntroScene(this));
        currentScene = scenes.get(IntroScene.SCENE_NAME);
        currentScene.play();
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

    @Override
    public void onScenePlayed(BaseScene scene) {

    }

    @Override
    public void onScenePaused(BaseScene scene) {
        if(scene.getType() == SceneType.INTRO) {
            System.out.println("Intro paused: starting game ...");
        }
    }
}
