package test;

public class SceneNotSetException extends Exception {

    @Override
    public String toString() {
        return "No scene set for rendering! \n " + super.toString();
    }
}
