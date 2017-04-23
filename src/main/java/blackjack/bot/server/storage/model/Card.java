package blackjack.bot.server.storage.model;

public class Card {
	final private String gameName;
	final private String card;

	public Card(String gameName, String card) {
		this.gameName = gameName;
		this.card = card;
	}

	public String getGameName() {
		return gameName;
	}

	public String getCard() {
		return card;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card1 = (Card) o;
		return card.equals(card1.card) && gameName.equals(card1.gameName);
	}

	@Override
	public int hashCode() {
		int result = gameName.hashCode();
		result = 31 * result + card.hashCode();
		return result;
	}
}
