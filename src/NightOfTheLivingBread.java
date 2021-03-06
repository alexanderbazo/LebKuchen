import config.Display;
import config.Fonts;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.*;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneState;
import game.scenes.SceneType;
import game.scenes.game.GameScene;
import game.scenes.intro.IntroScene;
import test.SceneNotSetException;
import utils.DebugInfo;
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
    private long lastFrameDrawnAt = -1;

    @Override
    public void initialize() {
        FontLoader.loadFonts(Fonts.FONT_DIR);
        setCanvasSize(Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT);
        hideCursor();
        initScenes();
        switchToScene(IntroScene.SCENE_NAME);
    }


    private void initScenes() {
        scenes = new WeakHashMap<>();
        scenes.put(GameScene.SCENE_NAME, new GameScene(this));
        scenes.put(IntroScene.SCENE_NAME, new IntroScene(this));
    }

    private void switchToScene(String sceneName) {
        BaseScene newScene = scenes.get(sceneName);
        if (newScene == null) {
            return;
        }
        if (currentScene != null && currentScene.getState() == SceneState.PLAYING) {
            currentScene.pause();
        }
        currentScene = newScene;
        currentScene.play();
    }

    @Override
    public void draw() {
        try {
            checkIfSceneIsSet();
        } catch (SceneNotSetException e) {
            e.printStackTrace();
            switchToScene(IntroScene.SCENE_NAME);
        }
        currentScene.render();
        updateFPSCounter();
        DebugInfo.draw();
        lastFrameDrawnAt = System.currentTimeMillis();
    }

    private void checkIfSceneIsSet() throws SceneNotSetException {
        if (currentScene == null) {
            throw new SceneNotSetException();
        }
    }

    private void updateFPSCounter() {
        if (lastFrameDrawnAt == -1) {
            return;
        }
        long frameTime = System.currentTimeMillis() - lastFrameDrawnAt;
        float fps = 1000 / frameTime;
        DebugInfo.set("FPS", fps);
    }

    @Override
    public void onMousePressed(MousePressedEvent event) {
        if (currentScene == null) {
            return;
        }
        currentScene.handleMousePressed(event);
    }

    @Override
    public void onMouseReleased(MouseReleasedEvent event) {
        if (currentScene == null) {
            return;
        }
        currentScene.handleMouseReleased(event);
    }

    @Override
    public void onMouseMoved(MouseMovedEvent event) {
        if (currentScene == null) {
            return;
        }
        currentScene.handleMouseMoved(event);
    }

    @Override
    public void onMouseDragged(MouseDraggedEvent event) {
        if (currentScene == null) {
            return;
        }
        currentScene.handleMouseDragged(event);
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if (currentScene == null) {
            return;
        }
        currentScene.handleKeyPressed(event);
    }

    @Override
    public void onKeyReleased(KeyReleasedEvent event) {
        if (currentScene == null) {
            return;
        }
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
        if (scene.getType() == SceneType.INTRO) {
            switchToScene(GameScene.SCENE_NAME);
            DebugInfo.show();
        }
    }
}
