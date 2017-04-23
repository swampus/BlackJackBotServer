package blackjack.bot.server.exception;

public class NotValidCardException extends RuntimeException {
	public NotValidCardException(String message) {
		super("Card is not valid: " + message);
	}
}
