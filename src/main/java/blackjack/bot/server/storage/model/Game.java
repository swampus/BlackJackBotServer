package blackjack.bot.server.storage.model;

public class Game {
	final private String name;
	final private String casinoName;

	public Game(String name, String casinoName) {
		this.name = name;
		this.casinoName = casinoName;
	}

	public String getName() {
		return name;
	}

	public String getCasinoName() {
		return casinoName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Game game = (Game) o;
		return !(casinoName != null ? !casinoName.equals(game.casinoName) : game.casinoName != null)
				&& !(name != null ? !name.equals(game.name) : game.name != null);

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (casinoName != null ? casinoName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Game{" +
				"name='" + name + '\'' +
				", casinoName='" + casinoName + '\'' +
				'}';
	}
}
