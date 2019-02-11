package blackjack.bot.server.exception;

public class CanNotDrawCardToBoxException extends RuntimeException {
    public CanNotDrawCardToBoxException(String message) {
        super(message);
    }
}
