package blackjack.bot.server.rest.model;

public class GameStateResponse {

	private String gameName;
	private Double count;

	public GameStateResponse(String gameName, Double count) {
		this.gameName = gameName;
		this.count = count;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}
}
