package blackjack.bot.server.service.strategy.card.calculation;

import blackjack.bot.server.storage.model.Card;
import org.junit.Test;
import rx.Observable;

import static org.junit.Assert.assertEquals;

public class HalvesSystemLogicTest {

	@Test
	public void testCalculation() throws Exception {
		HalvesSystem halvesSystem = new HalvesSystem();
		Observable<Card> cards = Observable.just(
				newCard("C_2"),
				newCard("C_3"),
				newCard("C_4"),
				newCard("C_5"),
				newCard("C_6"),
				newCard("C_7"),
				newCard("C_8"),
				newCard("C_9"),
				newCard("C_T"),
				newCard("C_A"));

		assertEquals(new Double(3.0), halvesSystem.calculate(cards).toBlocking().first());

		cards = Observable.just(
				newCard("C_T"),
				newCard("C_T"),
				newCard("C_T"),
				newCard("C_T"),
				newCard("C_T"),
				newCard("C_A"));

		assertEquals(new Double(-6.0), halvesSystem.calculate(cards).toBlocking().first());

		cards = Observable.just(
				newCard("C_4"),
				newCard("C_4"),
				newCard("C_4"),
				newCard("C_6"),
				newCard("C_6"),
				newCard("C_6"));

		assertEquals(new Double(6.0), halvesSystem.calculate(cards).toBlocking().first());

		cards = Observable.just(
				newCard("C_5"),
				newCard("C_5"),
				newCard("C_5"),
				newCard("C_5"),
				newCard("C_T"),
				newCard("C_T"));

		assertEquals(new Double(4.0), halvesSystem.calculate(cards).toBlocking().first());


		cards = Observable.just(
				newCard("C_T"),
				newCard("C_A"));

		assertEquals(new Double(-2.0), halvesSystem.calculate(cards).toBlocking().first());

	}


	private Card newCard(String value) {
		return new Card("q", value);
	}

}
