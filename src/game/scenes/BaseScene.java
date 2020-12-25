package game.scenes;

import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Rectangle;

import java.util.ArrayList;

public abstract class BaseScene implements InputHandler {

    private final GraphicsApp app;
    private final String sceneName;
    private final ArrayList<Actor> actors;
    private final Rectangle background;
    private SceneState sceneState;

    public BaseScene(GraphicsApp app, String sceneName, Color backgroundColor) {
        this.app = app;
        this.sceneName = sceneName;
        this.background = new Rectangle(0, 0, GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT, backgroundColor);
        this.actors = new ArrayList<>();
    }

    public String getName() {
        return sceneName;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }

    public Color getBackgroundColor() {
        return background.getColor();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.background.setColor(backgroundColor);
    }

    public void play() {
        sceneState = SceneState.PLAYING;
    }

    public void pause() {
        sceneState = SceneState.PAUSED;
    }

    public SceneState getState() {
        return sceneState;
    }

    public void render() {
        background.draw();
        for (Actor actor : actors) {
            actor.update();
        }
        for (Actor actor : actors) {
            actor.draw();
        }
    }

}
