package blackjack.bot.server.storage;

import blackjack.bot.server.storage.model.GameToken;
import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTokenStorageUnitTest {

	@Mock
	private RedisReactiveCommands<String, String> reactiveCommands;

	@InjectMocks
	private GameTokenStorage gameTokenStorage = new GameTokenStorage();

	@Test
	public void testPut() throws Exception {
		GameToken gameToken = new GameToken("gameName", "token");
		when(reactiveCommands.set("gameName_token", "token")).thenReturn(Observable.just("token"));
		assertNull(gameTokenStorage.put(gameToken).toBlocking().first());
		verify(reactiveCommands, times(1)).set("gameName_token", "token");
	}

	@Test
	public void testGet() throws Exception {
		GameToken gameToken = new GameToken("gameName", "token");
		when(reactiveCommands.get("gameName_token")).thenReturn(Observable.just("token"));
		assertEquals(gameToken, gameTokenStorage.get("gameName").toBlocking().first());
		verify(reactiveCommands, times(1)).get("gameName_token");
	}


	@Test
	public void testCreateGamesTokenNameKey() throws Exception {
		assertEquals("game_token", gameTokenStorage.createGamesTokenNameKey("game"));
	}

}
