package game.scenes.intro;

import de.ur.mi.oop.colors.Colors;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

public class IntroScene extends BaseScene {

    public IntroScene(SceneListener sceneListener) {
        super("Intro", SceneType.INTRO, sceneListener);
        setBackgroundColor(Colors.RED);
    }
}
