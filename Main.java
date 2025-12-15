/**
 * This class contains the main method and is where this program should be run from.
 */
public class Main{
    public static void main(String[] args){
        InputManager manager = new InputManager();
        while(manager.getRunning()){
            manager.getInput();
        }
    }
}