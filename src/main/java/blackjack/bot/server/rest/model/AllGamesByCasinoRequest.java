package blackjack.bot.server.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllGamesByCasinoRequest {
	@JsonProperty("casino_name")
	private String casinoName;

	public AllGamesByCasinoRequest(String casinoName) {
		this.casinoName = casinoName;
	}

	public String getCasinoName() {
		return casinoName;
	}
	public void setCasinoName(String casinoName) {
		this.casinoName = casinoName;
	}
}
