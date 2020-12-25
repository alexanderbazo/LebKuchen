package game.scenes.game;

import config.Assets;
import config.ColorScheme;
import de.ur.mi.oop.events.*;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

public class GameScene extends BaseScene {

    public static final String SCENE_NAME = "Game Scene";

    public GameScene(SceneListener sceneListener) {
        super(SCENE_NAME, SceneType.GAME, sceneListener);
        setBackgroundColor(ColorScheme.LINEN);
    }

    @Override
    public void handleMousePressed(MousePressedEvent event) {

    }

    @Override
    public void handleMouseReleased(MouseReleasedEvent event) {

    }

    @Override
    public void handleMouseMoved(MouseMovedEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseDraggedEvent event) {

    }

    @Override
    public void handleKeyPressed(KeyPressedEvent event) {

    }

    @Override
    public void handleKeyReleased(KeyReleasedEvent event) {

    }
}
