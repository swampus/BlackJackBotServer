package blackjack.bot.server.service;

import blackjack.bot.server.service.auth.GameAuth;
import blackjack.bot.server.storage.CardStorage;
import blackjack.bot.server.storage.model.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardManagementServiceUnitTest {

	@Mock
	private CardStorage cardStorage;

	@Mock
	private GameAuth gameAuth;

	@InjectMocks
	private CardManagementService cardManagementService = new CardManagementService(cardStorage, gameAuth);

	@Test
	public void testAddCard() throws Exception {
		when(cardStorage.put(new Card("gameName", "A"))).thenReturn(Observable.just(null));
		when(gameAuth.auth("gameName", "t")).thenReturn(Observable.just(null));

		assertNull(cardManagementService.addCard("gameName", "t", "A").toBlocking().first());

		verify(gameAuth, times(1)).auth("gameName", "t");
		verify(cardStorage, times(1)).put(new Card("gameName", "A"));

		verifyNoMoreInteractions(gameAuth, cardStorage);
	}

	@Test
	public void testGetAllCardsByGame() throws Exception {
		when(gameAuth.auth("gameName", "t")).thenReturn(Observable.just(null));
		when(cardStorage.getAllCardsInGame("gameName")).thenReturn(Observable.just(
				new Card("gm", "C_A"),
				new Card("gm", "C_2")));


		TestSubscriber<Card> testSubscriber = new TestSubscriber<>();
		cardManagementService.getAllCardsByGame("gameName", "t").subscribe(testSubscriber);
		testSubscriber.assertValues(new Card("gm", "C_A"),
				new Card("gm", "C_2"));
		testSubscriber.assertNoErrors();

		verify(gameAuth, times(1)).auth("gameName", "t");
		verify(cardStorage, times(1)).getAllCardsInGame("gameName");

		verifyNoMoreInteractions(gameAuth, cardStorage);
	}

	@Test
	public void testGetAceOutCountInGame() throws Exception {
		when(gameAuth.auth("gameName", "t")).thenReturn(Observable.just(null));
		when(cardStorage.getAllCardsInGame("gameName")).thenReturn(Observable.just(
				new Card("gm", "C_A"),
				new Card("gm", "C_A"),
				new Card("gm", "C_2")));

		assertEquals((Integer) 2, cardManagementService
				.getAceOutCountInGame("gameName", "t").toBlocking().first());

		verify(gameAuth, times(1)).auth("gameName", "t");
		verify(cardStorage, times(1)).getAllCardsInGame("gameName");

		verifyNoMoreInteractions(gameAuth, cardStorage);
	}

	@Test
	public void testGetAceOutCountInGameZero() throws Exception {
		when(gameAuth.auth("gameName", "t")).thenReturn(Observable.just(null));
		when(cardStorage.getAllCardsInGame("gameName")).thenReturn(Observable.just(
				new Card("gm", "C_3"),
				new Card("gm", "C_5"),
				new Card("gm", "C_2")));

		assertEquals((Integer) 0, cardManagementService
				.getAceOutCountInGame("gameName", "t").toBlocking().first());

		verify(gameAuth, times(1)).auth("gameName", "t");
		verify(cardStorage, times(1)).getAllCardsInGame("gameName");

		verifyNoMoreInteractions(gameAuth, cardStorage);
	}

	@Test
	public void testGetRealCount() throws Exception {
		when(gameAuth.auth("gameName", "t")).thenReturn(Observable.just(null));
		when(cardStorage.getAllCardsInGame("gameName")).thenReturn(Observable.just(
				new Card("gm", "C_A"),
				new Card("gm", "C_A"),
				new Card("gm", "C_2")));

		assertEquals((Integer) 3, cardManagementService
				.getCardOutCountInGame("gameName", "t").toBlocking().first());

		verify(gameAuth, times(1)).auth("gameName", "t");
		verify(cardStorage, times(1)).getAllCardsInGame("gameName");

		verifyNoMoreInteractions(gameAuth, cardStorage);
	}
}
