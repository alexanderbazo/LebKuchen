package game.scenes.game;

import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

public class GameScene extends BaseScene {

    public GameScene(SceneListener sceneListener) {
        super("Game Scene", SceneType.GAME, sceneListener);
    }

}
