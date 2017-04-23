package blackjack.bot.server.service;

import blackjack.bot.server.storage.CardStorage;
import blackjack.bot.server.storage.GameStorage;
import blackjack.bot.server.storage.GameTokenStorage;
import blackjack.bot.server.storage.model.Game;
import blackjack.bot.server.storage.model.GameToken;
import blackjack.bot.server.validator.GameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
//TODO: TEST
public class GameManagementService {

	@Autowired
	private GameStorage gameStorage;

	@Autowired
	private CardStorage cardStorage;

	@Autowired
	private GameValidator gameValidator;

	@Autowired
	private GameTokenStorage gameTokenStorage;

	@Autowired
	private UtilsService utilsService;

	@Autowired
	private AuthService authService;

	/**
	 * @param gameName   - game name
	 * @param casinoName - casino name
	 * @return Token for access game
	 */
	public Observable<String> startNewGame(String gameName, String casinoName) {
		Game newGame = new Game(gameName, casinoName);
		return gameStorage.get(gameName)
				.map(gameDB -> {
					gameStorage.put(gameValidator.databaseContainsEquals(gameDB, newGame));
					String randomId = utilsService.createRandomId();
					gameTokenStorage.put(new GameToken(gameName, randomId));
					return randomId;
				});

	}

	public Observable<Void> stopGame(String gameName, String gameToken) {
		return authService.auth(gameName, gameToken).map(t -> gameStorage.delete(gameName)
				.flatMap(g -> {
					cardStorage.deleteAllCardsInGame(gameName);
					return gameTokenStorage.delete(gameName);
				}).map(t -> null));
	}

	public Observable<Void> shuffleGame(String gameName, String gameToken) {
		authService.auth(gameName, gameToken)
				.map(t -> cardStorage.deleteAllCardsInGame(gameName)
						.map(q -> null));
	}


}
