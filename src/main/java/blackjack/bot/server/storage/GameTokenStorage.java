package blackjack.bot.server.storage;

import blackjack.bot.server.storage.model.GameToken;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
//Todo: TEST
public class GameTokenStorage extends AbstractStorage implements Storage<GameToken> {

	@Override
	public Observable<Void> put(GameToken value) {
		return reactiveCommands
				.set(createGamesTokenNameKey(value.getGameName()), value.getToken())
				.map(t -> null);
	}

	@Override
	public Observable<GameToken> get(String gameName) {
		return reactiveCommands.get(createGamesTokenNameKey(gameName))
				.map(t -> new GameToken(gameName, t));
	}

	@VisibleForTesting
	String createGamesTokenNameKey(String gameName) {
		return gameName + "_token";
	}

	@Override
	public Observable<Void> delete(String gameName) {
		return super.delete(createGamesTokenNameKey(gameName));
	}
}
