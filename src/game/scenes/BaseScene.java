package game.scenes;

import config.Display;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.events.KeyReleasedEvent;
import de.ur.mi.oop.graphics.GraphicsObject;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;
import game.actors.Actor;
import game.input.KeyboardInputHandler;

import java.util.ArrayList;

public abstract class BaseScene implements KeyboardInputHandler {

    private String sceneName;
    private SceneType sceneType;
    private SceneState sceneState;
    private ArrayList<Actor> actors;
    private ArrayList<KeyboardInputHandler> keyboardInputHandlers;
    private SceneListener sceneListener;
    private GraphicsObject background;

    public BaseScene(String sceneName, SceneType sceneType, SceneListener sceneListener) {
        this.sceneName = sceneName;
        this.sceneType = sceneType;
        this.sceneListener = sceneListener;
        this.background = new Rectangle(0, 0, Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT, Colors.BLACK);
        this.actors = new ArrayList<>();
        this.keyboardInputHandlers = new ArrayList<>();
    }

    public BaseScene(String sceneName, SceneType sceneType, SceneListener sceneListener, String backgroundImagePath) {
        this(sceneName, sceneType, sceneListener);
        Image image = new Image(0, 0, backgroundImagePath);
        image.setWidth(Display.WINDOW_WIDTH, true);
        image.setHeight(Display.WINDOW_HEIGHT, true);
        this.background = image;
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

    public void addActor(Actor actor) {
        actors.add(actor);
        if (actor instanceof KeyboardInputHandler) {
            keyboardInputHandlers.add((KeyboardInputHandler) actor);
        }
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
        for (Actor actor : actors) {
            actor.update();
        }
    }

    public void draw() {
        background.draw();
        for (Actor actor : actors) {
            actor.draw();
        }
    }

    @Override
    public void handleKeyPressed(KeyPressedEvent event) {
        for (KeyboardInputHandler handler : keyboardInputHandlers) {
            handler.handleKeyPressed(event);
        }
    }

    @Override
    public void handleKeyReleased(KeyReleasedEvent event) {
        for (KeyboardInputHandler handler : keyboardInputHandlers) {
            handler.handleKeyReleased(event);
        }
    }
}
