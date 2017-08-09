package blackjack.bot.server.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewGameRequest {

	@JsonProperty("game_name")
	private String gameName;

	@JsonProperty("casino_name")
	private String casinoName;

	public NewGameRequest(String gameName, String casinoName) {
		this.gameName = gameName;
		this.casinoName = casinoName;
	}

	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getCasinoName() {
		return casinoName;
	}
	public void setCasinoName(String casinoName) {
		this.casinoName = casinoName;
	}
}
