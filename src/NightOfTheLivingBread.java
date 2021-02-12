import config.Display;
import config.Fonts;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.KeyReleasedEvent;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneState;
import game.scenes.SceneType;
import game.scenes.game.GameScene;
import game.scenes.intro.IntroScene;
import utils.DebugInfo;
import utils.FontLoader;

import java.util.HashMap;

public class NightOfTheLivingBread extends GraphicsApp implements SceneListener {

    private long lastFrameDrawnAt = -1;

    private BaseScene currentScene;
    private HashMap<String, BaseScene> scenes;

    @Override
    public void initialize() {
        FontLoader.loadFonts(Fonts.FONT_DIR);
        setCanvasSize(Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT);
        DebugInfo.show();
        initScenes();
        switchToScene("INTRO");
    }

    private void initScenes() {
        scenes = new HashMap<>();
        scenes.put("INTRO", new IntroScene(this));
        scenes.put("GAME", new GameScene(this));
    }

    private void switchToScene(String sceneName) {
        BaseScene newScene = scenes.get(sceneName); // null if scene does not exists
        if ( newScene == null) {
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

        currentScene.render();

        // Debugging / Stats
        updateFPSCounter();
        DebugInfo.draw();
        lastFrameDrawnAt = System.currentTimeMillis();
    }

    private void updateFPSCounter() {
        if (lastFrameDrawnAt == -1) {
            return;
        }
        long frameTime = System.currentTimeMillis() - lastFrameDrawnAt;
        float fps = 1000f / frameTime;
        DebugInfo.set("FPS", fps);
    }

    @Override
    public void onScenePlayed(BaseScene scene) {
        System.out.println("Scene " + scene.getName() + " now playing");
    }

    @Override
    public void onScenePaused(BaseScene scene) {
        if (scene.getType() == SceneType.INTRO) {
            switchToScene("GAME");
        }
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if ( currentScene != null )  {
            currentScene.handleKeyPressed(event);
        }
    }

    @Override
    public void onKeyReleased(KeyReleasedEvent event) {
        if ( currentScene != null )  {
            currentScene.handleKeyReleased(event);
        }
    }

    public static void main(String[] args) {
        // Instanziiert eine Instanz dieser Klasse und startet die GraphicsApp
        GraphicsAppLauncher.launch();
    }
}
