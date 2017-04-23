package blackjack.bot.server.rest.controller;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Test {
	public static void main(String args[]) {
		RedisClient rc = RedisClient.create("redis://localhost");
		RedisReactiveCommands<String, String> conn = rc.connect().reactive();
	}

}
