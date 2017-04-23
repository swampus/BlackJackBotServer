package blackjack.bot.server.service;

import blackjack.bot.server.storage.model.Card;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HandAnalyzerServiceLogicTest {

	public static final int ACE_NINE_SUM = 110;
	public static final int TWO_ACE_SUM = 202;
	public static final int SINGLE_SUM = 7;
	public static final int TWENTY_ONE_SUM = 21;
	public static final int TWENTY_SUM = 20;
	public static final int BLACK_JACK_SUM = 111;
	public static final int SEVERAL_ACE_SUM = 207;
	public static final int SEVERAL_ACE_DRAW_SUM = 217;
	public static final int ACE_SOFT_SUM = 106;
	public static final int ACE_SOFT_DRAW_SUM = 118;

	private List<Card> TWO_ACE = ImmutableList.of(
			newCard("C_A"),
			newCard("C_A"));

	private List<Card> ACE_NINE = ImmutableList.of(
			newCard("C_A"),
			newCard("C_9"));

	private List<Card> SINGLE = ImmutableList.of(
			newCard("C_7"));

	private List<Card> BLACK_JACK = ImmutableList.of(
			newCard("C_T"),
			newCard("C_A"));

	private List<Card> TWENTY_ONE = ImmutableList.of(
			newCard("C_8"),
			newCard("C_3"),
			newCard("C_T"));

	private List<Card> TWENTY = ImmutableList.of(
			newCard("C_3"),
			newCard("C_3"),
			newCard("C_4"),
			newCard("C_T"));

	private List<Card> SEVERAL_ACE_SOFT = ImmutableList.of(
			newCard("C_A"),
			newCard("C_3"),
			newCard("C_A"),
			newCard("C_2"));

	private List<Card> SEVERAL_ACE_DRAW = ImmutableList.of(
			newCard("C_A"),
			newCard("C_3"),
			newCard("C_A"),
			newCard("C_T"),
			newCard("C_2"));

	private List<Card> ACE_SOFT = ImmutableList.of(
			newCard("C_A"),
			newCard("C_3"),
			newCard("C_2"));

	private List<Card> ACE_SOFT_DRAW = ImmutableList.of(
			newCard("C_A"),
			newCard("C_3"),
			newCard("C_2"),
			newCard("C_2"),
			newCard("C_T"));


	private HandAnalyzerService handAnalyzerService = new HandAnalyzerService();

	@Test
	public void testCalculateCardsValue() throws Exception {

		assertEquals(Integer.valueOf(ACE_NINE_SUM),
				handAnalyzerService.calculateCardsValue(ACE_NINE));

		assertEquals(Integer.valueOf(TWO_ACE_SUM),
				handAnalyzerService.calculateCardsValue(TWO_ACE));

		assertEquals(Integer.valueOf(SINGLE_SUM),
				handAnalyzerService.calculateCardsValue(SINGLE));

		assertEquals(Integer.valueOf(TWENTY_ONE_SUM),
				handAnalyzerService.calculateCardsValue(TWENTY_ONE));

		assertEquals(Integer.valueOf(TWENTY_SUM),
				handAnalyzerService.calculateCardsValue(TWENTY));

		assertEquals(Integer.valueOf(BLACK_JACK_SUM),
				handAnalyzerService.calculateCardsValue(BLACK_JACK));

		assertEquals(Integer.valueOf(SEVERAL_ACE_SUM),
				handAnalyzerService.calculateCardsValue(SEVERAL_ACE_SOFT));

		assertEquals(Integer.valueOf(SEVERAL_ACE_DRAW_SUM),
				handAnalyzerService.calculateCardsValue(SEVERAL_ACE_DRAW));

		assertEquals(Integer.valueOf(ACE_SOFT_SUM),
				handAnalyzerService.calculateCardsValue(ACE_SOFT));

		assertEquals(Integer.valueOf(ACE_SOFT_DRAW_SUM),
				handAnalyzerService.calculateCardsValue(ACE_SOFT_DRAW));

	}

	@Test
	public void testContainsAce() throws Exception {
		assertFalse(handAnalyzerService.containsAce(SINGLE_SUM));
		assertFalse(handAnalyzerService.containsAce(TWENTY_ONE_SUM));
		assertFalse(handAnalyzerService.containsAce(TWENTY_SUM));
		assertTrue(handAnalyzerService.containsAce(ACE_NINE_SUM));
		assertTrue(handAnalyzerService.containsAce(TWO_ACE_SUM));
		assertTrue(handAnalyzerService.containsAce(BLACK_JACK_SUM));
		assertTrue(handAnalyzerService.containsAce(SEVERAL_ACE_SUM));
		assertTrue(handAnalyzerService.containsAce(SEVERAL_ACE_DRAW_SUM));
		assertTrue(handAnalyzerService.containsAce(ACE_SOFT_SUM));
		assertTrue(handAnalyzerService.containsAce(ACE_SOFT_DRAW_SUM));
	}

	@Test
	public void testGetSoftMaxValue() throws Exception {
		assertEquals(Integer.valueOf(20), handAnalyzerService.getSoftMaxValue(ACE_NINE_SUM));
		assertEquals(Integer.valueOf(12), handAnalyzerService.getSoftMaxValue(TWO_ACE_SUM));
		assertEquals(Integer.valueOf(21), handAnalyzerService.getSoftMaxValue(BLACK_JACK_SUM));
		assertEquals(Integer.valueOf(17), handAnalyzerService.getSoftMaxValue(SEVERAL_ACE_SUM));
		assertEquals(Integer.valueOf(17), handAnalyzerService.getSoftMaxValue(SEVERAL_ACE_DRAW_SUM));
		assertEquals(Integer.valueOf(16), handAnalyzerService.getSoftMaxValue(ACE_SOFT_SUM));
		assertEquals(Integer.valueOf(18), handAnalyzerService.getSoftMaxValue(ACE_SOFT_DRAW_SUM));
	}

	@Test
	public void testGetSoftMinValue() throws Exception {
		assertEquals(Integer.valueOf(10), handAnalyzerService.getSoftMinValue(ACE_NINE_SUM));
		assertEquals(Integer.valueOf(2), handAnalyzerService.getSoftMinValue(TWO_ACE_SUM));
		assertEquals(Integer.valueOf(11), handAnalyzerService.getSoftMinValue(BLACK_JACK_SUM));
		assertEquals(Integer.valueOf(7), handAnalyzerService.getSoftMinValue(SEVERAL_ACE_SUM));
		assertEquals(Integer.valueOf(17), handAnalyzerService.getSoftMinValue(SEVERAL_ACE_DRAW_SUM));
		assertEquals(Integer.valueOf(6), handAnalyzerService.getSoftMinValue(ACE_SOFT_SUM));
		assertEquals(Integer.valueOf(18), handAnalyzerService.getSoftMinValue(ACE_SOFT_DRAW_SUM));
	}

	private Card newCard(String val) {
		return new Card("casino", val);
	}
}
