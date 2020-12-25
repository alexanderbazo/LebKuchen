package game.actors;

import game.scenes.BaseScene;

public abstract class Actor {

    private final BaseScene hostScene;

    public Actor(BaseScene hostScene) {
        this.hostScene = hostScene;
    }
    
    public void update() {
        
    }
    
    public void draw() {
        
    }
}
