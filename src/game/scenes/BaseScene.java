package game.scenes;

import config.GameConfig;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.GraphicsObject;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;
import game.actors.Actor;

import java.util.ArrayList;

public abstract class BaseScene implements InputHandler {

    private final String sceneName;
    private final SceneType sceneType;
    private final SceneListener sceneListener;
    private final ArrayList<Actor> actors;
    private GraphicsObject background;
    private SceneState sceneState;

    public BaseScene(String sceneName, SceneType sceneType, SceneListener sceneListener) {
        this.sceneName = sceneName;
        this.sceneType = sceneType;
        this.sceneListener = sceneListener;
        this.actors = new ArrayList<>();
        this.background = new Rectangle(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT, Colors.BLACK);
    }

    public BaseScene(String sceneName, SceneType sceneType, SceneListener sceneListener, String backgroundImagePath) {
        this(sceneName, sceneType, sceneListener);
        Image image = new Image(0, 0, backgroundImagePath);
        image.setWidth(GameConfig.WINDOW_WIDTH, true);
        image.setHeight(GameConfig.WINDOW_HEIGHT, true);
        this.background = image;
    }

    public String getName() {
        return sceneName;
    }

    public SceneType getType() {
        return sceneType;
    }

    public Color getBackgroundColor() {
        return background.getColor();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.background.setColor(backgroundColor);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }

    public void play() {
        sceneState = SceneState.PLAYING;
        sceneListener.onScenePlayed(this);
    }

    public void pause() {
        sceneState = SceneState.PAUSED;
        sceneListener.onScenePaused(this);
    }

    public SceneState getState() {
        return sceneState;
    }

    public void render() {
        background.draw();
        update();
        draw();
    }

    private void update() {
        if (sceneState == SceneState.PAUSED) {
            return;
        }
        for (Actor actor : actors) {
            actor.update();
        }
    }

    private void draw() {
        for (Actor actor : actors) {
            actor.draw();
        }
    }

}
