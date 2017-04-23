package blackjack.bot.server.storage;

import blackjack.bot.server.storage.model.Card;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class CardStorage extends AbstractStorage implements Storage<Card> {

	@Override
	public Observable<Void> put(Card value) {
		return reactiveCommands.lpush(createGameCardListName(value.getGameName()), value.getCard())
				.map(t -> null);
	}

	@Override
	public Observable<Card> get(String gameName) {
		return reactiveCommands.lrange(createGameCardListName(gameName), 0, Long.MAX_VALUE)
				.map(card -> new Card(gameName, card));
	}

	public Observable<Void> deleteAllCardsInGame(String gameName) {
		return reactiveCommands.del(createGameCardListName(gameName)).map(t -> null);
	}

	public Observable<Card> getAllCardsInGame(String gameName) {
		return reactiveCommands.lrange(createGameCardListName(gameName), 0, Long.MAX_VALUE)
				.map(t -> new Card(gameName,t));
	}

	@VisibleForTesting
	String createGameCardListName(String gameName) {
		return gameName + "_cards";
	}

}
