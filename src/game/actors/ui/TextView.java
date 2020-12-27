package game.actors.ui;

import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Label;
import game.actors.Actor;
import game.scenes.BaseScene;

public class TextView extends Actor {

    private final Label label;

    public TextView(int x, int y, BaseScene hostScene) {
        super(x, y, hostScene);
        label = new Label(x, y, "");
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.label.setPosition(x, y);
    }

    @Override
    public void draw() {
        label.draw();
    }

    public void setText(String text) {
        label.setText(text);
    }

    public String getText() {
        return label.getText();
    }

    public void setColor(Color color) {
        label.setColor(color);
    }

    public void setTextSize(int size) {
        label.setFontSize(size);
    }

    public int getTextSize() {
        return label.getFontSize();
    }

    public void setFont(String fontName) {
        label.setFont(fontName);
    }

    public int getWidth() {
        return label.getWidthEstimate();
    }

    public int getHeight() {
        return label.getHeightEstimate();
    }

}
