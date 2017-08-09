package blackjack.bot.server.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NextTurnTipRequest {

	@JsonProperty("game_name")
	private String gameName;

	public NextTurnTipRequest(String gameName) {
		this.gameName = gameName;
	}

	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
}
