package game.scenes;

import config.Assets;
import config.GameConfig;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.*;
import game.actors.TextView;

public class IntroScene extends BaseScene {

    public static final String SCENE_NAME = "Intro Scene";
    private TextView version;
    private TextView hint;

    public IntroScene(SceneListener sceneListener) {
        super(SCENE_NAME, SceneType.INTRO, sceneListener, Assets.INTRO_SCENE_BACKGROUND_IMAGE);
        initVersionNumber();
        initHint();
    }

    private void initVersionNumber() {
        version = new TextView(5, 5, this);
        version.setText("Version: " + GameConfig.GAME_VERSION);
        version.setColor(Colors.WHITE);
        version.setTextSize(12);
        version.setFont("Creepster");
        addActor(version);
    }

    private void initHint() {
        hint = new TextView(75, 375, this);
        hint.setText("Press SPACE to continue");
        hint.setColor(Colors.WHITE);
        hint.setTextSize(32);
        hint.setFont("Creepster");
        addActor(hint);
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
        if(event.getKeyCode() == KeyPressedEvent.VK_SPACE) {
            pause();
        }
    }

    @Override
    public void handleKeyReleased(KeyReleasedEvent event) {

    }
}
