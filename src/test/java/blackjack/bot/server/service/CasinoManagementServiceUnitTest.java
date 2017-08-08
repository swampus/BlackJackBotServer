package blackjack.bot.server.service;

import blackjack.bot.server.service.model.CasinoGames;
import blackjack.bot.server.storage.CardStorage;
import blackjack.bot.server.storage.CasinoStorage;
import blackjack.bot.server.storage.GameStorage;
import blackjack.bot.server.storage.GameTokenStorage;
import blackjack.bot.server.storage.model.Casino;
import blackjack.bot.server.storage.model.Game;
import blackjack.bot.server.validator.CasinoValidator;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CasinoManagementServiceUnitTest {

	@Mock
	private CasinoStorage casinoStorage;

	@Mock
	private GameStorage gameStorage;

	@Mock
	private GameTokenStorage gameTokenStorage;

	@Mock
	private CardStorage cardStorage;

	@Mock
	private CasinoValidator casinoValidator;

	@InjectMocks
	private CasinoManagementService casinoManagementService = new CasinoManagementService();

	@Test
	public void testAddCasino() throws Exception {
		Casino casino = new Casino("casinoName", "A");
		Casino newCasino = new Casino("casinoName", "B");

		when(casinoStorage.get("casinoName")).thenReturn(Observable.just(casino));
		when(casinoValidator.databaseContainsEquals(casino, newCasino)).thenReturn(newCasino);

		assertNull(casinoManagementService.addCasino(newCasino).toBlocking().first());
		verify(casinoStorage, times(1)).get("casinoName");
		verify(casinoStorage, times(1)).put(newCasino);
		verify(casinoValidator, times(1)).databaseContainsEquals(casino, newCasino);
	}

	@Test
	public void testDeleteCasino() throws Exception {
		when(casinoStorage.delete("casinoName")).thenReturn(Observable.just(null));
		when(gameStorage.getAllByCasinoName("casinoName")).thenReturn(
				Observable.just(
						new Game("A1", "casinoName"),
						new Game("A2", "casinoName")));


		when(gameStorage.delete("A1")).thenReturn(Observable.just(null));
		when(gameTokenStorage.delete("A1")).thenReturn(Observable.just(null));
		when(cardStorage.deleteAllCardsInGame("A1")).thenReturn(Observable.just(null));

		when(gameStorage.delete("A2")).thenReturn(Observable.just(null));
		when(gameTokenStorage.delete("A2")).thenReturn(Observable.just(null));
		when(cardStorage.deleteAllCardsInGame("A2")).thenReturn(Observable.just(null));
		when(gameStorage.deleteAllGamesInCasino("casinoName")).thenReturn(Observable.just(null));

		casinoManagementService.deleteCasino("casinoName").subscribe();
		verify(gameStorage, times(1)).deleteAllGamesInCasino("casinoName");
		verify(casinoStorage, times(1)).delete("casinoName");

		verify(gameStorage, times(1)).getAllByCasinoName("casinoName");

		verify(gameStorage, times(1)).delete("A1");
		verify(gameTokenStorage, times(1)).delete("A1");
		verify(cardStorage, times(1)).deleteAllCardsInGame("A1");

		verify(gameStorage, times(1)).delete("A2");
		verify(gameTokenStorage, times(1)).delete("A2");
		verify(cardStorage, times(1)).deleteAllCardsInGame("A2");

		verifyNoMoreInteractions(cardStorage, casinoStorage, gameTokenStorage, gameStorage);
	}

	@Test
	public void testGetAllCasinosAndGames() throws Exception {

		when(casinoStorage.getAll()).thenReturn(
				Observable.just(
						new Casino("A1", "B1"),
						new Casino("A2", "B2"),
						new Casino("A3", "B3")));

		when(gameStorage.getAllByCasinoName("A1")).thenReturn(
				Observable.just(
						new Game("gm1", "q1"),
						new Game("gm2", "q2"),
						new Game("gm3", "q3")));


		when(gameStorage.getAllByCasinoName("A2")).thenReturn(
				Observable.just(
						new Game("game4", "qqq1"),
						new Game("game5", "qqq2")));

		when(gameStorage.getAllByCasinoName("A3")).thenReturn(
				Observable.just(
						new Game("game6", "qqq3")));

		Observable<CasinoGames> casGames = casinoManagementService.getAllCasinosAndGames();

		assertEquals(new Casino("A1", "B1"), casGames.toBlocking().first().getCasino());
		assertEquals(new Casino("A3", "B3"), casGames.toBlocking().last().getCasino());
		assertEquals(new Game("gm1", "q1"),
				casGames.toBlocking().first().getGames().toBlocking().first());
		assertEquals(new Game("gm3", "q3"),
				casGames.toBlocking().first().getGames().toBlocking().last());

		/*
			Can not test with equals - testSubscriber,
			test with toBlocking, subscribing to Observable several times
		*/
		verify(casinoStorage, times(1)).getAll();
		verify(gameStorage, times(4)).getAllByCasinoName("A1");
		verify(gameStorage, times(1)).getAllByCasinoName("A2");
		verify(gameStorage, times(1)).getAllByCasinoName("A3");

		verifyNoMoreInteractions(casinoStorage, gameStorage);
	}
}
