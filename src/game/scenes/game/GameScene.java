package game.scenes.game;

import config.Display;
import game.actors.player.Player;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

public class GameScene extends BaseScene {

    public GameScene(SceneListener sceneListener) {
        super("Game Scene", SceneType.GAME, sceneListener);
        Player player = new Player(Display.WINDOW_WIDTH / 2, Display.WINDOW_HEIGHT / 2, this);
        addActor(player);
    }

}
