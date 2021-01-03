package game.actors.ui;

import de.ur.mi.oop.graphics.Image;

import java.util.ArrayList;

public class Animation {

    private final int framesPerSprite;
    private final Image[] sprites;
    private int framesSinceLastSpriteChange;
    private int currentSpriteIndex;
    private AnimationState state;

    public Animation(float x, float y, float width, float height, String[] sprites, int framesPerSprite) {
        this.framesPerSprite = framesPerSprite;
        this.framesSinceLastSpriteChange = 0;
        this.sprites = Animation.loadSprites(x, y, width, height, sprites);
        this.currentSpriteIndex = 0;
        this.state = AnimationState.PAUSED;
    }

    public void play() {
        state = AnimationState.PLAYING;
    }

    public void pause() {
        state = AnimationState.PAUSED;
    }

    public float getXPos() {
        return sprites[currentSpriteIndex].getXPos();
    }

    public float getYPos() {
        return sprites[currentSpriteIndex].getYPos();
    }

    public float getWidth() {
        return sprites[currentSpriteIndex].getWidth();
    }

    public float getHeight() {
        return sprites[currentSpriteIndex].getHeight();
    }

    public void move(float x, float y) {
        for(Image image: sprites) {
            image.move(x, y);
        }
    }

    public void draw() {
        if (state == AnimationState.PAUSED) {
            return;
        }
        updateSprite();
        sprites[currentSpriteIndex].draw();
    }

    private void updateSprite() {
        if (framesSinceLastSpriteChange == framesPerSprite) {
            currentSpriteIndex++;
            if (currentSpriteIndex == sprites.length) {
                currentSpriteIndex = 0;
            }
            framesSinceLastSpriteChange = 0;
        } else {
            framesSinceLastSpriteChange++;
        }
    }

    private static Image[] loadSprites(float x, float y, float width, float height, String[] sprites) {
        Image[] spriteImages = new Image[sprites.length];
        for(int i = 0; i < sprites.length; i++) {
            Image image = new Image(x, y, sprites[i]);
            image.setWidth(width, true);
            image.setHeight(height, true);
            spriteImages[i] = image;
        }
        return spriteImages;
    }


}
