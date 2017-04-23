package blackjack.bot.server.storage.model;

public class GameToken {

	final private String gameName;
	final private String token;

	public GameToken(String gameName, String token) {
		this.token = token;
		this.gameName = gameName;
	}

	public String getToken() {
		return token;
	}

	public String getGameName() {
		return gameName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GameToken gameToken = (GameToken) o;
		return !(gameName != null ? !gameName.equals(gameToken.gameName) : gameToken.gameName != null)
				&& !(token != null ? !token.equals(gameToken.token) : gameToken.token != null);
	}

	@Override
	public int hashCode() {
		int result = gameName != null ? gameName.hashCode() : 0;
		result = 31 * result + (token != null ? token.hashCode() : 0);
		return result;
	}
}
