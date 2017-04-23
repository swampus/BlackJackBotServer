package blackjack.bot.server.service.model;

import blackjack.bot.server.storage.model.Casino;
import blackjack.bot.server.storage.model.Game;
import rx.Observable;

import java.util.List;

public class CasinoGames {

	private final Casino casino;
	private final Observable<Game> games;

	public CasinoGames(Casino casino, Observable<Game> games) {
		this.casino = casino;
		this.games = games;
	}

	public Casino getCasino() {
		return casino;
	}

	public Observable<Game> getGames() {
		return games;
	}

	@Override
	public String toString() {
		return "CasinoGames{" +
				"casino=" + casino +
				", games=" + games +
				'}';
	}
}
