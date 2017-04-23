package blackjack.bot.server.validator;

import blackjack.bot.server.exception.NotValidCardException;
import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.storage.model.Card;
import org.springframework.stereotype.Service;

@Service
public class CardValidator implements Validator<Card> {
	public Card checkCard(Card card) {
		try {
			Cards.valueOf(card.getCard());
		} catch (IllegalArgumentException e) {
			throw new NotValidCardException(card.getCard());
		}
		return card;
	}
}
