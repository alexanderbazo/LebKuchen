package utils;

import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

import java.util.LinkedHashMap;

public class DebugInfo {

    private static final int X_POSITION = 5;
    private static final int Y_POSITION = 5;
    private static final int MIN_WIDTH = 1;
    private static final int MIN_HEIGHT = 1;
    private static final int PADDING = 10;
    private static final Color BACKGROUND_COLOR = new Color(250, 250, 250, 150);
    private static final Color BORDER_COLOR = new Color(200, 200, 200, 150);
    private static final float BORDER_WEIGHT = 1;
    private static final String FONT_NAME = "Monospaced";
    private static final Color FONT_COLOR = new Color(30, 30, 30);
    private static final int FONT_SIZE = 10;
    private static final String TITLE = "### Debug Info ###";
    private static DebugInfo instance;

    private boolean isVisible;
    private Rectangle background;
    private LinkedHashMap<String, Label> values;

    private DebugInfo() {
        background = new Rectangle(X_POSITION, Y_POSITION, MIN_WIDTH, MIN_HEIGHT, BACKGROUND_COLOR);
        background.setBorder(BORDER_COLOR, BORDER_WEIGHT);
        values = new LinkedHashMap<>();
        isVisible = false;
        updateOrAddLabel(TITLE, "");
        pack();
    }

    public static DebugInfo getInstance() {
        if (instance == null) {
            instance = new DebugInfo();
        }
        return instance;
    }

    private void updateOrAddLabel(String name, String value) {
        Label label = values.get(name);
        if (label == null) {
            label = createLabel(name, value);
            values.put(name, label);
        } else {
            label.setText(name + ": " + value);
        }
    }

    private Label createLabel(String name, String value) {
        Label label = new Label(0, 0, name + ": " + value, FONT_COLOR);
        if(name.equals(TITLE)) {
            label.setText(TITLE);
        }
        label.setFont(FONT_NAME);
        label.setFontSize(FONT_SIZE);
        return label;
    }

    private void pack() {
        float minHeight = 0;
        float minWidth = 0;
        float x = background.getXPos() + PADDING;
        float y = background.getYPos() + PADDING;
        for (Label label : values.values()) {
            label.setPosition(x, y);
            y += FONT_SIZE + PADDING;
            minHeight += FONT_SIZE + PADDING;
            if (minWidth < label.getWidthEstimate()) {
                minWidth = label.getWidthEstimate();
            }
        }
        background.setWidth(minWidth + 2 * PADDING);
        background.setHeight(minHeight - FONT_SIZE);
    }

    public void set(String name, String value) {
        updateOrAddLabel(name, value);
        pack();
    }

    public void set(String name, boolean value) {
        set(name, String.valueOf(value));
    }

    public void set(String name, short value) {
        set(name, String.valueOf(value));
    }

    public void set(String name, int value) {
        set(name, String.valueOf(value));
    }

    public void set(String name, float value) {
        set(name, String.valueOf(value));
    }

    public void set(String name, double value) {
        set(name, String.valueOf(value));
    }

    public void set(String name, char value) {
        set(name, String.valueOf(value));
    }

    public void remove(String name) {
        values.remove(name);
        pack();
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public void draw() {
        if (!isVisible) {
            return;
        }
        background.draw();
        for (Label label : values.values()) {
            label.draw();
        }
    }

}
