package blackjack.bot.server.rest.model;

import blackjack.bot.server.storage.model.Card;

/**
 * Card leaves Deck, that change game stategy
 */
public class SingleCardOutRequest {
	private String gameToken;
	private String gameName;
	private String cardValue;

	public SingleCardOutRequest(String gameToken, String gameName, String cardValue) {
		this.gameToken = gameToken;
		this.gameName = gameName;
		this.cardValue = cardValue;
	}

	public String getGameToken() {
		return gameToken;
	}

	public void setGameToken(String gameToken) {
		this.gameToken = gameToken;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getCardValue() {
		return cardValue;
	}

	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}
}
