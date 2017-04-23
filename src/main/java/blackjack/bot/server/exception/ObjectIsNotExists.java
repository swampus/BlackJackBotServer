package blackjack.bot.server.exception;

public class ObjectIsNotExists extends RuntimeException {
	public ObjectIsNotExists(String message) {
		super("Object is not exists, can not continue operation: " + message);
	}
}
