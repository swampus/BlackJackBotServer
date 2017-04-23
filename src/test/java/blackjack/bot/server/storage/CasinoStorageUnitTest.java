package blackjack.bot.server.storage;

import blackjack.bot.server.storage.model.Casino;
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
public class CasinoStorageUnitTest {

	@Mock
	private RedisReactiveCommands<String, String> reactiveCommands;

	@InjectMocks
	private CasinoStorage casinoStorage = new CasinoStorage();

	@Test
	public void testPut() throws Exception {
		Casino casino = new Casino("casino", "address");
		when(reactiveCommands.set("casino", "address")).thenReturn(Observable.just("casino"));
		when(reactiveCommands.lpush("casinoList", "casino")).thenReturn(Observable.just((long) 23));
		assertNull(casinoStorage.put(casino).toBlocking().first());

		verify(reactiveCommands, times(1)).set("casino", "address");
		verify(reactiveCommands, times(1)).lpush("casinoList", "casino");
	}

	@Test
	public void testGet() throws Exception {
		Casino casino = new Casino("casino", "address");
		when(reactiveCommands.get("casino")).thenReturn(Observable.just("address"));
		assertEquals(casino, casinoStorage.get("casino").toBlocking().first());
		verify(reactiveCommands, times(1)).get("casino");
	}

	@Test
	public void testNull() throws Exception {
		when(reactiveCommands.get("casino")).thenReturn(Observable.just(null));
		assertEquals(new Casino("casino", null), casinoStorage.get("casino").toBlocking().first());
		verify(reactiveCommands, times(1)).get("casino");
	}


	@Test
	public void testGetAll() throws Exception {
		when(reactiveCommands.lrange("casinoList", 0, Long.MAX_VALUE))
				.thenReturn(Observable.just("c1", "c2", "c3", "c4"));

		when(reactiveCommands.get("c1")).thenReturn(Observable.just("a1"));
		when(reactiveCommands.get("c2")).thenReturn(Observable.just("a2"));
		when(reactiveCommands.get("c3")).thenReturn(Observable.just("a3"));
		when(reactiveCommands.get("c4")).thenReturn(Observable.just("a4"));

		Observable<Casino> result = casinoStorage.getAll();

		TestSubscriber<Casino> testSubscriber = new TestSubscriber<>();
		result.subscribe(testSubscriber);

		testSubscriber.assertValues(new Casino("c1", "a1"),
				new Casino("c2", "a2"),
				new Casino("c3", "a3"),
				new Casino("c4", "a4"));
		testSubscriber.assertNoErrors();

		verify(reactiveCommands, times(1)).lrange("casinoList", 0, Long.MAX_VALUE);
		verify(reactiveCommands, times(1)).get("c1");
		verify(reactiveCommands, times(1)).get("c2");
		verify(reactiveCommands, times(1)).get("c3");
		verify(reactiveCommands, times(1)).get("c4");
	}

	@Test
	public void testDelete() throws Exception {
		when(reactiveCommands.del("casino")).thenReturn(Observable.just(Long.MAX_VALUE));
		when(reactiveCommands.lrem("casinoList", 1, "casino")).thenReturn(Observable.just(null));

		assertNull(casinoStorage.delete("casino").toBlocking().first());

		verify(reactiveCommands, times(1)).del("casino");
		verify(reactiveCommands, times(1)).lrem("casinoList", 1, "casino");
	}
}
