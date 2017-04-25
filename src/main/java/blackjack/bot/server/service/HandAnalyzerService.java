package blackjack.bot.server.service;

import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.storage.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HandAnalyzerService {

	public static final int FIRST = 0;
	public static final int SECOND = 1;
	public static final int ACE_VALUE_WHEN_SOFT_DRAW = 10;
	public static final int CARDS_IN_DECK = 52;
	public static final int DECKS_IN_CLASSIC_BLACK_JACK = 6;

	public Integer calculateCardsValue(List<Card> cards) {
		return cards.stream().collect(Collectors.summingInt(card ->
				Cards.valueOf(card.getCard()).getValue()));
	}

	public Boolean containsAce(int sum) {
		return (sum > 100);
	}

	public Integer getSoftMaxValue(int sum) {
		final Integer softMinValue = getSoftMinValue(sum);
		int possibleSum = softMinValue + ACE_VALUE_WHEN_SOFT_DRAW;
		if (possibleSum > 21) {
			return softMinValue;
		}
		return possibleSum;
	}

	public Integer getSoftMinValue(int sum) {
		String removedAces = String.valueOf(sum).substring(SECOND);
		if (removedAces.substring(FIRST).equals("0")) {
			return Integer.valueOf(removedAces.substring(SECOND));
		} else {
			return Integer.valueOf(removedAces);
		}
	}

	public Integer getDeckCountLeftInGame(Integer cardsOutCount) {
		Integer deckOutCount = cardsOutCount / CARDS_IN_DECK;
		return DECKS_IN_CLASSIC_BLACK_JACK - deckOutCount;
	}

	public Integer getRealCount(Integer deckCountLeftInGame, Integer countInGame){
		return countInGame / deckCountLeftInGame;
	}

}
