package blackjack.bot.server.rest.model.ennum;

public enum Cards {

	C_A(101),
	C_K(10),
	C_Q(10),
	C_J(10),
	C_T(10),
	C_9(9),
	C_8(8),
	C_7(7),
	C_6(6),
	C_5(5),
	C_4(4),
	C_3(3),
	C_2(2);

	private final Integer value;

	Cards(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
