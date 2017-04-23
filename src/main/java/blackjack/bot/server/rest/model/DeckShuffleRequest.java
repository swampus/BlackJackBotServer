package blackjack.bot.server.rest.model;

/**
 * Deck is shuffle, calculation
 * statistics for that game is flush
 */
public class DeckShuffleRequest {
	private String gameName;
	private String gameToken;

	public DeckShuffleRequest(String gameName, String gameToken) {
		this.gameName = gameName;
		this.gameToken = gameToken;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameToken() {
		return gameToken;
	}

	public void setGameToken(String gameToken) {
		this.gameToken = gameToken;
	}
}
