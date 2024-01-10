package Game;
import java.util.*;
public interface Controller {
	public void setPlayerName(String playerName);
	public void startGame();
	public void endGame();
	public String[] getNPCsInfo();
	public String[] getPlayersInfo();
	public String[] getInventorysInfo();
	public String[] getFeaturesInfo();
	public String[] getCurrentRoomsInfo();
	public void select(String object_ID);
	public String getNarration();
	public void save(String savefile_ID);
	public void load(String savefile_ID);
	public boolean isOver();

	}
