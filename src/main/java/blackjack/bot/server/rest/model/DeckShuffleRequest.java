package blackjack.bot.server.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Deck is shuffle, calculation
 * statistics for that game is flush
 */
public class DeckShuffleRequest {

	@JsonProperty("game_name")
	private String gameName;

	@JsonProperty("game_token")
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
