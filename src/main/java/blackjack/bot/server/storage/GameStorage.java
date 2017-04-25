package blackjack.bot.server.storage;

import blackjack.bot.server.storage.model.Game;
import com.google.common.annotations.VisibleForTesting;
import rx.Observable;

import javax.annotation.Resource;

@Resource
public class GameStorage extends AbstractStorage implements Storage<Game> {

	@Override
	public Observable<Void> put(Game game) {
		return reactiveCommands.set(game.getName(), game.getCasinoName())
				.flatMap(s -> reactiveCommands
						.lpush(createGamesInCasinoKey(game.getCasinoName()), game.getName())
						.map(t -> null));

	}

	@VisibleForTesting
	String createGamesInCasinoKey(String casinoName) {
		return casinoName + "_games";
	}

	@Override
	public Observable<Game> get(String gameName) {
		return reactiveCommands.get(gameName).map(t -> new Game(gameName, t));
	}

	@Override
	public Observable<Void> delete(String gameName) {
		return reactiveCommands.get(gameName)
				.flatMap(casinoName -> reactiveCommands
						.lrem(createGamesInCasinoKey(casinoName), 1, gameName)
						.flatMap(t -> super.delete(gameName)));
	}

	public Observable<Game> getAllByCasino(String casinoName) {
		return reactiveCommands
				.lrange(createGamesInCasinoKey(casinoName), 0, Long.MAX_VALUE)
				.map(name -> new Game(name, casinoName));
	}

	public Observable<Void> deleteAllGamesInCasino(String casinoName) {
		return reactiveCommands
				.del(createGamesInCasinoKey(casinoName)).map(t -> null);
	}


}
