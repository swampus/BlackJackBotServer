package blackjack.bot.server.exception;

public class GameOperationAuthFailedException extends RuntimeException {
	public GameOperationAuthFailedException(String gameName) {
		super("Auth for game: " + gameName
				+ "failed. Not possible to perform operation!");
	}
}
