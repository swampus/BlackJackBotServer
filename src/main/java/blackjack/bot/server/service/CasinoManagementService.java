package blackjack.bot.server.service;

import blackjack.bot.server.annotation.Restricted;
import blackjack.bot.server.service.model.CasinoGames;
import blackjack.bot.server.storage.CardStorage;
import blackjack.bot.server.storage.CasinoStorage;
import blackjack.bot.server.storage.GameStorage;
import blackjack.bot.server.storage.GameTokenStorage;
import blackjack.bot.server.storage.model.Casino;
import blackjack.bot.server.validator.CasinoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Restricted
@Service
public class CasinoManagementService {

	@Autowired
	private CasinoStorage casinoStorage;

	@Autowired
	private GameStorage gameStorage;

	@Autowired
	private GameTokenStorage gameTokenStorage;

	@Autowired
	private CardStorage cardStorage;

	@Autowired
	private CasinoValidator casinoValidator;

	public Observable<Void> addCasino(Casino newCasino) {
		return casinoStorage.get(newCasino.getName())
				.map(casinoDB -> casinoStorage
						.put(casinoValidator.databaseContainsEquals(casinoDB, newCasino)))
				.map(t -> null);
	}

	public Observable<Void> deleteCasino(String casinoName) {
		return casinoStorage.delete(casinoName)
				.map(q -> gameStorage.deleteAllGamesInCasino(casinoName))
				.flatMap(t -> gameStorage.getAllByCasinoName(casinoName)
						.map(game -> {
							final String name = game.getName();
							gameStorage.delete(name);
							gameTokenStorage.delete(name);
							cardStorage.deleteAllCardsInGame(name);
							return null;
						}));
	}

	public Observable<CasinoGames> getAllCasinosAndGames() {
		return casinoStorage.getAll()
				.map(casino -> new CasinoGames(casino,
						gameStorage.getAllByCasinoName(casino.getName())));
	}


}
