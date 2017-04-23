package blackjack.bot.server.redis;

import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class SecurityStorage {

	@Autowired
	private RedisReactiveCommands<String, String> reactiveCommands;

	public Observable<Void> createGame(String gameName, String token) {
		return reactiveCommands.set(gameName, token).map(t -> null);
	}
}
