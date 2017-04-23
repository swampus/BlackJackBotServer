package blackjack.bot.server.storage.model;

public class Casino {

	private final String name;
	private final String address;

	public Casino(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Casino casino = (Casino) o;
		return !(address != null ? !address.equals(casino.address) : casino.address != null)
				&& !(name != null ? !name.equals(casino.name) : casino.name != null);

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Casino{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
