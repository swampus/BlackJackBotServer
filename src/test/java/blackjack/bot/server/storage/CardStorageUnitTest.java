package blackjack.bot.server.storage;


import blackjack.bot.server.storage.model.Card;
import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardStorageUnitTest {

	@Mock
	private RedisReactiveCommands<String, String> reactiveCommands;

	@InjectMocks
	private CardStorage cardStorage = new CardStorage();

	@Test
	public void testPut() throws Exception {
		Card card = new Card("key", "A");

		when(reactiveCommands.lpush("key_cards", "A")).thenReturn(Observable.just((long) 10));

		assertNull(cardStorage.put(card).toBlocking().first());

		verify(reactiveCommands, times(1)).lpush("key_cards", "A");
	}

	@Test
	public void testGet() throws Exception {
		Card cardA = new Card("key", "A");
		Card cardK = new Card("key", "K");
		Card cardJ = new Card("key", "J");

		when(reactiveCommands.lrange("key_cards", 0, Long.MAX_VALUE))
				.thenReturn(Observable.just("A", "K", "K", "J"));

		Observable<Card> obs = cardStorage.get("key");
		TestSubscriber<Card> testSubscriber = new TestSubscriber<>();

		obs.subscribe(testSubscriber);
		testSubscriber.assertNoErrors();
		testSubscriber.assertValueCount(4);
		testSubscriber.assertValues(cardA, cardK, cardK, cardJ);

		verify(reactiveCommands, times(1)).lrange("key_cards", 0, Long.MAX_VALUE);
	}

	@Test
	public void testGetAllCardsByGame() throws Exception {
		when(reactiveCommands
				.lrange("gameName_cards", 0, Long.MAX_VALUE)).thenReturn(Observable.just("A", "A", "10"));

		Observable<Card> cards = cardStorage.getAllCardsInGame("gameName");
		TestSubscriber<Card> testSubscriber = new TestSubscriber<>();
		cards.subscribe(testSubscriber);
		testSubscriber.assertValues(
				new Card("gameName", "A"),
				new Card("gameName", "A"),
				new Card("gameName", "10"));
		testSubscriber.assertNoErrors();

		verify(reactiveCommands, times(1)).lrange("gameName_cards", 0, Long.MAX_VALUE);

	}


	@Test
	public void testCreateKey() throws Exception {
		assertEquals("testKey_cards", cardStorage.createGameCardListName("testKey"));
	}
}
