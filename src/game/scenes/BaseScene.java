package game.scenes;

import config.Display;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Rectangle;

public abstract class BaseScene {

    private String sceneName;
    private SceneType sceneType;
    private SceneState sceneState;
    private SceneListener sceneListener;
    private Rectangle background;

    public BaseScene(String sceneName, SceneType sceneType, SceneListener sceneListener) {
        this.sceneName = sceneName;
        this.sceneType = sceneType;
        this.sceneListener = sceneListener;
        this.background = new Rectangle(0, 0, Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT, Colors.BLACK);
    }

    public String getName() {
        return sceneName;
    }

    public SceneType getType() {
        return sceneType;
    }

    public SceneState getState() {
        return sceneState;
    }

    public void setBackgroundColor(Color backgroundColor) {
        background.setColor(backgroundColor);
    }

    public void play() {
        sceneState = SceneState.PLAYING;
        sceneListener.onScenePlayed(this);
    }

    public void pause() {
        sceneState = SceneState.PAUSED;
        sceneListener.onScenePaused(this);
    }

    public void render() {
        update();
        draw();
    }

    private void update() {
        if (sceneState == SceneState.PAUSED) {
            return;
        }
        // Update all actors
    }

    public void draw() {
        background.draw();
        // Draw all actors
    }

}
