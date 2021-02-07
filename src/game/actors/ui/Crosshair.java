package game.actors.ui;

import config.Assets;
import config.Weapons;
import de.ur.mi.oop.events.MouseDraggedEvent;
import de.ur.mi.oop.events.MouseMovedEvent;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.events.MouseReleasedEvent;
import de.ur.mi.oop.graphics.Image;
import game.actors.Actor;
import game.input.MouseInputHandler;
import game.scenes.BaseScene;

public class Crosshair extends Actor implements MouseInputHandler {

    private Image body;

    public Crosshair(int x, int y, BaseScene hostScene) {
        super(x, y, hostScene);
        body = new Image(x, y, Assets.CROSSHAIR_IMAGE);
        body.setWidth(Weapons.CROSSHAIR_WIDTH, true);
        body.setHeight(Weapons.CROSSHAIR_HEIGHT, true);
    }

    @Override
    public void draw() {
        body.draw();
    }

    @Override
    public void handleMousePressed(MousePressedEvent event) {

    }

    @Override
    public void handleMouseReleased(MouseReleasedEvent event) {

    }

    @Override
    public void handleMouseMoved(MouseMovedEvent event) {
        body.setPosition(event.getXPos(), event.getYPos());
    }

    @Override
    public void handleMouseDragged(MouseDraggedEvent event) {

    }
}
