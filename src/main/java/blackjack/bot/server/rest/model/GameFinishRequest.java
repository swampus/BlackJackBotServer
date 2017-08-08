package blackjack.bot.server.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameFinishRequest {
	@JsonProperty("game_name")
	private String gameName;

	@JsonProperty("game_token")
	private String gameToken;

	public GameFinishRequest(String gameName, String gameToken) {
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
