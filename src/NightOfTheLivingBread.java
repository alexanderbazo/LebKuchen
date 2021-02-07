import config.ColorScheme;
import config.Display;
import config.Fonts;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import utils.DebugInfo;
import utils.FontLoader;

public class NightOfTheLivingBread extends GraphicsApp {

    private long lastFrameDrawnAt = -1;

    @Override
    public void initialize() {
        FontLoader.loadFonts(Fonts.FONT_DIR);
        setCanvasSize(Display.WINDOW_WIDTH, Display.WINDOW_HEIGHT);
        DebugInfo.show();
    }

    @Override
    public void draw() {
        drawBackground(ColorScheme.GREY);
        updateFPSCounter();
        DebugInfo.draw();
        lastFrameDrawnAt = System.currentTimeMillis();
    }

    private void updateFPSCounter() {
        if (lastFrameDrawnAt == -1) {
            return;
        }
        long frameTime = System.currentTimeMillis() - lastFrameDrawnAt;
        float fps = 1000f / frameTime;
        DebugInfo.set("FPS", fps);
    }


    public static void main(String[] args) {
        // Instanziiert eine Instanz dieser Klasse und startet die GraphicsApp
        GraphicsAppLauncher.launch();
    }

}
