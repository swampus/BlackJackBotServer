package blackjack.bot.server.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetGameStateRequest {
	@JsonProperty("game_name")
	private String gameName;
	@JsonProperty("game_token")
	private String gameToken;
	@JsonProperty("system")
	private String system;

	public GetGameStateRequest(String gameName, String gameToken, String system) {
		this.gameName = gameName;
		this.gameToken = gameToken;
		this.system = system;
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

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
}
