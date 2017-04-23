package blackjack.bot.server.service.strategy.card.calculation;

import blackjack.bot.server.storage.model.Card;
import rx.Observable;

public interface CardCalculationSystem {
	Observable<Double> calculate(Observable<Card> cards);
}
