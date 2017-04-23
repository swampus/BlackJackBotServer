package blackjack.bot.server.exception;

public class UnknownCalculationSystem extends RuntimeException {
	public UnknownCalculationSystem(String message) {
		super("Unknown CalculationSystem: " + message);
	}
}
