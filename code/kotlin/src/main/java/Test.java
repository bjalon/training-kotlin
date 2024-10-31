import eu.centralpay.domain.Cup;
import eu.centralpay.domain.Game;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Game game = new Game(Arrays.asList("Benjamin", "Reece", "Fadoua"), new Cup());
		game.playUntilAUserHasWon();
	}
}
