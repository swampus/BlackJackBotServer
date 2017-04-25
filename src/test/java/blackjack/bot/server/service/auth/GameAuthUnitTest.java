package blackjack.bot.server.service.auth;

import blackjack.bot.server.exception.GameOperationAuthFailedException;
import blackjack.bot.server.storage.GameTokenStorage;
import blackjack.bot.server.storage.model.GameToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameAuthUnitTest {

	@Mock
	private GameTokenStorage gameTokenStorage;

	@InjectMocks
	private GameAuth gameAuth = new GameAuth();

	@Test
	public void testAuth() throws Exception {
		when(gameTokenStorage.get("gm")).thenReturn(Observable.just(new GameToken("gm", "tk")));

		assertNull(gameAuth.auth("gm", "tk").toBlocking().first());

		verify(gameTokenStorage, times(1)).get("gm");

		verifyNoMoreInteractions(gameTokenStorage);
	}

	@Test
	public void testAuthException() throws Exception {
		when(gameTokenStorage.get("gm")).thenReturn(Observable.just(null));

		try {
			gameAuth.auth("gm", "tk").toBlocking().first();
			fail();
		} catch (GameOperationAuthFailedException e) {
			assertEquals("Auth for game: gm failed. Not possible to perform operation!", e.getMessage());
		}

		verify(gameTokenStorage, times(1)).get("gm");

		verifyNoMoreInteractions(gameTokenStorage);
	}

}
