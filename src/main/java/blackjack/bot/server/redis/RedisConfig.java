package blackjack.bot.server.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

	@Value("$redis.server.host")
	private String host;

	@Bean
	public RedisClient redisClient() {
		System.out.println(host);
		return RedisClient.create(host);
	}

	@Bean
	public RedisReactiveCommands<String, String> reactiveCommands(RedisClient redisClient) {
		return redisClient.connect().reactive();
	}

}
