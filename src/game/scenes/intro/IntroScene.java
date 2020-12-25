package game.scenes.intro;

import config.Assets;
import config.ColorScheme;
import config.Fonts;
import config.GameConfig;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.*;
import game.actors.TextView;
import game.scenes.BaseScene;
import game.scenes.SceneListener;
import game.scenes.SceneType;

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
        version = new TextView(20, 20, this);
        version.setText("Version: " + GameConfig.GAME_VERSION);
        version.setColor(ColorScheme.WHITE);
        version.setTextSize(Fonts.SIZE_SMALL);
        version.setFont(Fonts.DEFAULT_FONT);
        addActor(version);
    }

    private void initHint() {
        hint = new TextView(100, 800, this);
        hint.setText("Press SPACE to continue");
        hint.setColor(ColorScheme.WHITE);
        hint.setTextSize(Fonts.SIZE_LARGE);
        hint.setFont(Fonts.DEFAULT_FONT);
        addActor(hint);
    }

    @Override
    public void handleKeyPressed(KeyPressedEvent event) {
        super.handleKeyPressed(event);
        if (event.getKeyCode() == KeyPressedEvent.VK_SPACE) {
            pause();
        }
    }
}
