package blackjack.bot.server.service.strategy;

import blackjack.bot.server.service.strategy.model.Hit;
import blackjack.bot.server.service.strategy.model.NextAction;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.List;

@Component
public class BaseStrategy {

	/**
	 * 2,3,4,5,6,7,8,9,10,A
	 * 0,1,2,3,4,5,6,7,8,9
	 */

	public Observable<NextAction> whatNext(Integer dealerCardValue, Integer playerCardValues) {
		List<NextAction> actionsFrom4to8 = ImmutableList.of(
				new Hit(), new Hit(), new Hit(),
				new Hit(), new Hit(), new Hit(),
				new Hit(), new Hit(), new Hit(),
				new Hit(), new Hit());

		return null;
	}

}
