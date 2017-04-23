package blackjack.bot.server.service;

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
	private AuthService authService;

	public Observable<Void> addCard(String gameName, String token, String card) {
		return authService.auth(gameName, token)
				.map(t -> cardStorage.put(new Card(gameName, card)))
				.map(t -> null);
	}

	public Observable<Card> getAllCardsByGame(String gameName, String token) {
		return authService.auth(gameName, token)
				.flatMap(t -> cardStorage.getAllCardsInGame(gameName));
	}



}
