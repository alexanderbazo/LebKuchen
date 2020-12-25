package game.scenes.game;

import config.ColorScheme;
import config.GameConfig;
import game.actors.Player;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

public class GameScene extends BaseScene {

    public static final String SCENE_NAME = "Game Scene";

    private Player player;

    public GameScene(SceneListener sceneListener) {
        super(SCENE_NAME, SceneType.GAME, sceneListener);
        setBackgroundColor(ColorScheme.LINEN);
        player = new Player(GameConfig.WINDOW_WIDTH/2, GameConfig.WINDOW_HEIGHT/2, this);
        addActor(player);
    }

}
