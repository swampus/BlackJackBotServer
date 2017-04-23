package blackjack.bot.server.service;

import blackjack.bot.server.exception.GameOperationAuthFailedException;
import blackjack.bot.server.storage.GameTokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class AuthService {

	@Autowired
	private GameTokenStorage gameTokenStorage;

	public Observable<Void> auth(String gameName, String token) {
		return gameTokenStorage.get(gameName).flatMap(
				gameToken -> {
					if (gameToken == null || !gameToken.getToken().equals(token)) {
						throw new GameOperationAuthFailedException(gameName);
					}
					return null;
				});
	}
}
