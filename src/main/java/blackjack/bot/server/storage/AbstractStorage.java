package blackjack.bot.server.storage;


import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

public abstract class AbstractStorage {

	@Autowired
	protected RedisReactiveCommands<String, String> reactiveCommands;

	public Observable<Void> delete(String key) {
		return reactiveCommands.del(key).map(t -> null);
	}

}
