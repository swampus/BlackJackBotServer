package blackjack.bot.server.service;

import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.service.auth.GameAuth;
import blackjack.bot.server.storage.CardStorage;
import blackjack.bot.server.storage.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class CardManagementService {

	@Autowired
	private CardStorage cardStorage;

	@Autowired
	private GameAuth gameAuth;

	public Observable<Void> addCard(String gameName, String token, String card) {
		return gameAuth.auth(gameName, token)
				.map(t -> cardStorage.put(new Card(gameName, card)))
				.map(t -> null);
	}

	public Observable<Card> getAllCardsByGame(String gameName, String token) {
		return gameAuth.auth(gameName, token)
				.flatMap(t -> cardStorage.getAllCardsInGame(gameName));
	}


	public Observable<Integer> getAceOutCountInGame(String gameName, String token) {
		return gameAuth.auth(gameName, token)
				.flatMap(t -> cardStorage.getAllCardsInGame(gameName).filter(
						card -> card.getCard().equals(Cards.C_A.name())).count()
				);
	}


}
