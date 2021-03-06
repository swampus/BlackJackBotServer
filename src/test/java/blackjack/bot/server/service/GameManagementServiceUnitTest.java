package blackjack.bot.server.service;

import blackjack.bot.server.exception.ObjectAlreadyExistsException;
import blackjack.bot.server.service.auth.GameAuth;
import blackjack.bot.server.storage.CardStorage;
import blackjack.bot.server.storage.GameStorage;
import blackjack.bot.server.storage.GameTokenStorage;
import blackjack.bot.server.storage.model.Game;
import blackjack.bot.server.storage.model.GameToken;
import blackjack.bot.server.validator.GameValidator;
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
public class GameManagementServiceUnitTest {

	@Mock
	private GameStorage gameStorage;

	@Mock
	private CardStorage cardStorage;

	@Mock
	private GameValidator gameValidator;

	@Mock
	private GameTokenStorage gameTokenStorage;

	@Mock
	private UtilsService utilsService;

	@Mock
	private GameAuth gameAuth;


	@InjectMocks
	private GameManagementService gameManagementService = new GameManagementService();

	@Test
	public void testStartNewGame() throws Exception {
		Game newGame = new Game("gameName", "casinoName");
		GameToken gameToken = new GameToken("gameName", "random_id");

		when(gameStorage.get("gameName")).thenReturn(Observable.just(null));
		when(gameStorage.put(newGame)).thenReturn(Observable.just(null));
		when(utilsService.createRandomId()).thenReturn("random_id");
		when(gameValidator.databaseContainsEquals(null, newGame)).thenReturn(newGame);

		when(gameTokenStorage.put(gameToken)).thenReturn(Observable.just(null));

		assertEquals("random_id",
				gameManagementService.startNewGame("gameName", "casinoName").toBlocking().first());

		verify(gameStorage, times(1)).get("gameName");
		verify(gameStorage, times(1)).put(newGame);
		verify(utilsService, times(1)).createRandomId();
		verify(gameValidator, times(1)).databaseContainsEquals(null, newGame);
		verify(gameTokenStorage, times(1)).put(gameToken);

		verifyNoMoreInteractions(gameStorage, utilsService, gameValidator);
	}

	@Test
	public void testStartNewGameException() throws Exception {
		Game newGame = new Game("gameName", "casinoName");
		GameToken gameToken = new GameToken("gameName", "random_id");

		when(gameStorage.get("gameName")).thenReturn(Observable.just(newGame));
		when(gameStorage.put(newGame)).thenReturn(Observable.just(null));
		when(utilsService.createRandomId()).thenReturn("random_id");
		when(gameValidator.databaseContainsEquals(newGame, newGame))
				.thenThrow(new ObjectAlreadyExistsException("game"));

		when(gameTokenStorage.put(gameToken)).thenReturn(Observable.just(null));

		try {
			gameManagementService.startNewGame("gameName", "casinoName").toBlocking().first();
			fail();
		} catch (ObjectAlreadyExistsException e) {
			assertEquals("Already Exists in database! - game", e.getMessage());
		}

		verify(gameStorage, times(1)).get("gameName");
		verify(gameValidator, times(1)).databaseContainsEquals(newGame, newGame);

		verifyNoMoreInteractions(gameStorage, utilsService, gameValidator);
	}

	@Test
	public void testStopGame() throws Exception {
		when(gameStorage.delete("gameName")).thenReturn(Observable.just(null));
		when(cardStorage.deleteAllCardsInGame("gameName")).thenReturn(Observable.just(null));
		when(gameAuth.auth("gameName", "token")).thenReturn(Observable.just(null));
		when(gameTokenStorage.delete("gameName")).thenReturn(Observable.just(null));

		assertNull(gameManagementService.stopGame("gameName", "token").toBlocking().first());

		verify(gameAuth, times(1)).auth("gameName", "token");
		verify(gameStorage, times(1)).delete("gameName");
		verify(cardStorage, times(1)).deleteAllCardsInGame("gameName");
		verify(gameTokenStorage, times(1)).delete("gameName");

		verifyNoMoreInteractions(gameStorage, utilsService, gameValidator, gameTokenStorage);
	}

	@Test
	public void testShuffleGame() throws Exception {
		when(cardStorage.deleteAllCardsInGame("gm")).thenReturn(Observable.just(null));
		when(gameAuth.auth("gm", "token")).thenReturn(Observable.just(null));

		assertNull(gameManagementService.shuffleGame("gm", "token").toBlocking().first());

		verify(gameAuth, times(1)).auth("gm", "token");
		verify(cardStorage, times(1)).deleteAllCardsInGame("gm");

		verifyNoMoreInteractions(gameStorage, utilsService, gameValidator);
	}

	@Test
	public void getAllGamesInCasino() throws Exception {
		when(gameStorage.getAllByCasinoName("casino"))
				.thenReturn(Observable.just(new Game("1", "2"), new Game("3", "4")));

		assertEquals(gameManagementService.getAllGamesInCasino("casino").toList().toBlocking().first(),
				Observable.just(new Game("1", "2"), new Game("3", "4")).toList().toBlocking().first());

		verify(gameStorage, times(1)).getAllByCasinoName("casino");

		verifyNoMoreInteractions(gameStorage, utilsService, gameValidator);
	}

}
