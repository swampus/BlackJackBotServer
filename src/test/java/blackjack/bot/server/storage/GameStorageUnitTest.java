package blackjack.bot.server.storage;

import blackjack.bot.server.storage.model.Game;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameStorageUnitTest {

	@Mock
	private RedisReactiveCommands<String, String> reactiveCommands;

	@InjectMocks
	private GameStorage gameStorage = new GameStorage();

	@Test
	public void testPut() throws Exception {
		Game game = new Game("name", "casinoName");
		when(reactiveCommands.set("name", "casinoName")).thenReturn(Observable.just("casino"));
		when(reactiveCommands.lpush("casinoName_games", game.getName())).thenReturn(Observable.just((long) 3));

		assertNull(gameStorage.put(game).toBlocking().first());

		verify(reactiveCommands, times(1)).set("name", "casinoName");
		verify(reactiveCommands, times(1)).lpush("casinoName_games", "name");

		verifyNoMoreInteractions(reactiveCommands);
	}

	@Test
	public void testGet() throws Exception {
		Game game = new Game("name", "address");
		when(reactiveCommands.get("name")).thenReturn(Observable.just("address"));
		assertEquals(game, gameStorage.get("name").toBlocking().first());
		verify(reactiveCommands, times(1)).get("name");

		verifyNoMoreInteractions(reactiveCommands);
	}

	@Test
	public void testGetNull() throws Exception {
		when(reactiveCommands.get("name")).thenReturn(Observable.just(null));
		assertEquals(new Game("name", null), gameStorage.get("name").toBlocking().first());
		verify(reactiveCommands, times(1)).get("name");

		verifyNoMoreInteractions(reactiveCommands);
	}

	@Test
	public void testGetAllByCasino() throws Exception {
		when(reactiveCommands.lrange("casinoName_games", 0, Long.MAX_VALUE))
				.thenReturn(Observable.just("g1", "g2", "g3", "g4"));

		when(reactiveCommands.get("g1")).thenReturn(Observable.just("casinoName"));
		when(reactiveCommands.get("g2")).thenReturn(Observable.just("casinoName"));
		when(reactiveCommands.get("g3")).thenReturn(Observable.just("casinoName"));
		when(reactiveCommands.get("g4")).thenReturn(Observable.just("casinoName"));

		Observable<Game> result = gameStorage.getAllByCasino("casinoName");

		TestSubscriber<Game> testSubscriber = new TestSubscriber<>();
		result.subscribe(testSubscriber);

		testSubscriber.assertValues(new Game("g1", "casinoName"),
				new Game("g2", "casinoName"),
				new Game("g3", "casinoName"),
				new Game("g4", "casinoName"));
		testSubscriber.assertNoErrors();

		verify(reactiveCommands, times(1)).lrange("casinoName_games", 0, Long.MAX_VALUE);

		verifyNoMoreInteractions(reactiveCommands);

	}

	@Test
	public void testDelete() throws Exception {
		when(reactiveCommands.get("gameName")).thenReturn(Observable.just("casinoName"));
		when(reactiveCommands.lrem("casinoName_games", 1, "gameName")).thenReturn(Observable.just((long) 4));
		when(reactiveCommands.del("gameName")).thenReturn(Observable.just((long) 4));

		assertNull(gameStorage.delete("gameName").toBlocking().first());

		verify(reactiveCommands, times(1)).get("gameName");
		verify(reactiveCommands, times(1)).lrem("casinoName_games", 1, "gameName");
		verify(reactiveCommands, times(1)).del("gameName");
	}

	@Test
	public void testCreateGamesInCasinoKey() throws Exception {
		assertEquals("name_games", gameStorage.createGamesInCasinoKey("name"));
	}

	@Test
	public void testDeleteCasinoGameList() throws Exception {
		when(reactiveCommands.del("casinoName_games")).thenReturn(Observable.just(null));
		assertNull(gameStorage.deleteAllGamesInCasino("casinoName").toBlocking().first());

		verify(reactiveCommands, times(1)).del("casinoName_games");
	}

}
