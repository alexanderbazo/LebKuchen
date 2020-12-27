package game.scenes;

import config.Display;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.*;
import de.ur.mi.oop.graphics.GraphicsObject;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;
import game.actors.Actor;
import game.input.KeyboardInputHandler;
import game.input.MouseInputHandler;

import java.util.ArrayList;

public abstract class BaseScene implements KeyboardInputHandler, MouseInputHandler {

    private final String sceneName;
    private final SceneType sceneType;
    private final SceneListener sceneListener;
    private final ArrayList<Actor> actors;
    private final ArrayList<KeyboardInputHandler> keyboardInputHandlers;
    private final ArrayList<MouseInputHandler> mouseInputHandlers;
    private GraphicsObject background;
    private SceneState sceneState;

    public BaseScene(String sceneName, SceneType sceneType, SceneListener sceneListener) {
        this.sceneName = sceneName;
        this.sceneType = sceneType;
        this.sceneListener = sceneListener;
        this.actors = new ArrayList<>();
        this.keyboardInputHandlers = new ArrayList<>();
        this.mouseInputHandlers = new ArrayList<>();
        this.background = new Rectangle(0, 0, Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT, Colors.BLACK);
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

    public Color getBackgroundColor() {
        return background.getColor();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.background.setColor(backgroundColor);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
        if (actor instanceof KeyboardInputHandler) {
            keyboardInputHandlers.add((KeyboardInputHandler) actor);
        }
        if (actor instanceof MouseInputHandler) {
            mouseInputHandlers.add((MouseInputHandler) actor);
        }
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    // @TODO Use streams to filter actor list for given type
    public ArrayList<Actor> getActorOfType(Class<? extends Actor> type) {
        ArrayList<Actor> relevantActors = new ArrayList<>();
        for (Actor actor : actors) {
            if (type.isInstance(actor)) {
                relevantActors.add(actor);
            }
        }
        return relevantActors;
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
        if (actor instanceof KeyboardInputHandler) {
            keyboardInputHandlers.remove((KeyboardInputHandler) actor);
        }
        if (actor instanceof MouseInputHandler) {
            mouseInputHandlers.remove((MouseInputHandler) actor);
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

    public SceneState getState() {
        return sceneState;
    }

    public void render() {
        background.draw();
        update();
        draw();
    }

    public void update() {
        if (sceneState == SceneState.PAUSED) {
            return;
        }
        for (Actor actor : actors) {
            actor.update();
        }
    }

    public void draw() {
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

    @Override
    public void handleMousePressed(MousePressedEvent event) {
        for (MouseInputHandler handler : mouseInputHandlers) {
            handler.handleMousePressed(event);
        }
    }

    @Override
    public void handleMouseReleased(MouseReleasedEvent event) {
        for (MouseInputHandler handler : mouseInputHandlers) {
            handler.handleMouseReleased(event);
        }
    }

    @Override
    public void handleMouseMoved(MouseMovedEvent event) {
        for (MouseInputHandler handler : mouseInputHandlers) {
            handler.handleMouseMoved(event);
        }
    }

    @Override
    public void handleMouseDragged(MouseDraggedEvent event) {
        for (MouseInputHandler handler : mouseInputHandlers) {
            handler.handleMouseDragged(event);
        }
    }
}
