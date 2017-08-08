package blackjack.bot.server.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCasinoRequest {
	@JsonProperty("casino_name")
	private String casinoName;
	@JsonProperty("address")
	private String address;

	public NewCasinoRequest(String casinoName, String address) {
		this.casinoName = casinoName;
		this.address = address;
	}

	public String getCasinoName() {
		return casinoName;
	}
	public void setCasinoName(String casinoName) {
		this.casinoName = casinoName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
