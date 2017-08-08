package blackjack.bot.server.service.strategy;

import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.service.strategy.model.DDouble;
import blackjack.bot.server.service.strategy.model.Hit;
import blackjack.bot.server.service.strategy.model.Hold;
import blackjack.bot.server.service.strategy.model.NextAction;
import blackjack.bot.server.service.strategy.model.Surrender;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BaseStrategy {

	/**
	 * 2,3,4,5,6,7,8,9,10,A
	 * 0,1,2,3,4,5,6,7,8,9
	 */

	public ImmutableList<NextAction> whatNext(Integer dealerCardValue, Integer playerCardValues) {
//		List<NextAction> actionsFrom4to8 = ImmutableList.of(
//				new Hit(), new Hit(), new Hit(),
//				new Hit(), new Hit(), new Hit(),
//				new Hit(), new Hit(), new Hit(),
//				new Hit(), new Hit());

		List<NextAction> actionsFrom4to8 = ImmutableList.of();
		List<NextAction> actions9 = ImmutableList.of();
		List<NextAction> actions10 = ImmutableList.of();
		List<NextAction> actions11 = ImmutableList.of();
		List<NextAction> actions12 = ImmutableList.of();
		List<NextAction> actions13 = ImmutableList.of();
		List<NextAction> actions14 = ImmutableList.of();
		List<NextAction> actions15 = ImmutableList.of();
		List<NextAction> actions16 = ImmutableList.of();
		List<NextAction> actions17 = ImmutableList.of();
		List<NextAction> actions18plus = ImmutableList.of();

		for (int i = 0; i < 12; i++) {
			actionsFrom4to8.add(hit());
			actions11.add(dDouble());
			actions17.add(hold());
			actions18plus.add(hold());
			if (0 == i) {
				actions9.add(hit());
			} else if (i < 3) {
				actions12.add(hit());
				actions14.add(hold());
			} else if (i < 5) {
				actions9.add(dDouble());
				actions12.add(hold());
				actions13.add(hold());
				actions14.add(hold());
			} else if (i < 6) {
				actions14.add(hold());
				actions15.add(hold());
				actions16.add(hold());
			} else if (i < 9) {
				actions10.add(dDouble());
				actions12.add(hit());
				actions14.add(hit());
				actions15.add(hit());
				actions16.add(hit());
			} else if (i < 11) {
				actions10.add(hit());
				actions12.add(hit());
				actions14.add(hit());
				actions15.add(surrender());
				actions16.add(surrender());
			}
		}


		return ImmutableList.copyOf(actionsFrom4to8);
	}

	Hit hit() {
		return new Hit();
	}

	Hold hold() {
		return new Hold();
	}

	DDouble dDouble() {
		return new DDouble();
	}

	Surrender surrender(){
		return new Surrender();
	}


}
