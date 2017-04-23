package blackjack.bot.server.service;

import blackjack.bot.server.storage.CardStorage;
import blackjack.bot.server.storage.model.Card;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardManagementServiceUnitTest {

	@Mock
	private CardStorage cardStorage;

	@Mock
	private AuthService authService;

	@InjectMocks
	private CardManagementService cardManagementService = new CardManagementService();

	@Test
	public void testAddCard() throws Exception {
		when(cardStorage.put(new Card("gameName", "A"))).thenReturn(Observable.just(null));
		when(authService.auth("gameName", "t")).thenReturn(Observable.just(null));

		assertNull(cardManagementService.addCard("gameName", "t", "A").toBlocking().first());

		verify(authService, times(1)).auth("gameName", "t");
		verify(cardStorage, times(1)).put(new Card("gameName", "A"));
	}

	@Test
	public void testGetAllCardsByGame() throws Exception {
		when(authService.auth("gameName", "t")).thenReturn(Observable.just(null));
		when(cardStorage.getAllCardsInGame("gameName")).thenReturn(Observable.just(
				new Card("gm", "A"),
				new Card("gm", "B")));


		TestSubscriber<Card> testSubscriber = new TestSubscriber<>();
		cardManagementService.getAllCardsByGame("gameName", "t").subscribe(testSubscriber);
		testSubscriber.assertValues(new Card("gm", "A"),
				new Card("gm", "B"));
		testSubscriber.assertNoErrors();

		verify(authService, times(1)).auth("gameName", "t");
		verify(cardStorage, times(1)).getAllCardsInGame("gameName");
	}
}
