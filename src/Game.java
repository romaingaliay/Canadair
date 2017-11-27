import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

    private Game() { super("JCanadair"); }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game(), 800, 600, false);
        app.setVSync(true);
        app.setShowFPS(false);
        app.start();
    }

    public void initStatesList(GameContainer container) throws SlickException {
        //addState(new MainScreenGameState());
        //addState(new MapGameState());
        //addState(new BattleGameState());
    }
}
