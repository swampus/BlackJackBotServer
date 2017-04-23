package blackjack.bot.server.validator;

import blackjack.bot.server.exception.NotValidCardException;
import blackjack.bot.server.storage.model.Card;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class CardValidatorUnitTest {

	@Test
	public void testCheckCard() throws Exception {
		Card validCard = new Card("gm", "C_1");
		CardValidator cardValidator = new CardValidator();

		assertEquals(validCard, cardValidator.checkCard(validCard));
	}

	@Test
	public void testCheckCardException() throws Exception {
		Card validCard = new Card("gm", "HA");
		CardValidator cardValidator = new CardValidator();

		try {
			cardValidator.checkCard(validCard);
			fail();
		} catch (NotValidCardException e) {
			assertEquals("Card is not valid: HA", e.getMessage());
		}

	}
}
