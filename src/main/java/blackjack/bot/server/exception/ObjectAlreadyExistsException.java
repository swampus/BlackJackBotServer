package blackjack.bot.server.exception;


public class ObjectAlreadyExistsException extends RuntimeException {
	public ObjectAlreadyExistsException(String message) {
		super("Already Exists in database! - " + message);
	}
}
