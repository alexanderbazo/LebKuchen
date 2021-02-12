package game.scenes.intro;

import config.Assets;
import de.ur.mi.oop.events.KeyPressedEvent;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

public class IntroScene extends BaseScene {

    public IntroScene(SceneListener sceneListener) {
        super("Intro", SceneType.INTRO, sceneListener, Assets.INTRO_SCENE_BACKGROUND_IMAGE);
    }

    @Override
    public void handleKeyPressed(KeyPressedEvent event) {
        super.handleKeyPressed(event);
        if( event.getKeyCode() == KeyPressedEvent.VK_SPACE ) {
            pause();
        }
    }
}
