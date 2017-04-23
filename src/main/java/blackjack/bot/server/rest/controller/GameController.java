package blackjack.bot.server.rest.controller;

import blackjack.bot.server.storage.GameStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameStorage gameStorage;

	@RequestMapping(value = "/create/{gameName}/{gameToken}", method = RequestMethod.POST)
	@ResponseBody
	public String createGame(@PathVariable("gameName") String gameName,
	                         @PathVariable("gameToken") String gameToken) {
//		gameStorage.putCard(gameName,"A").subscribe();
//		gameStorage.putCard(gameName,"10").subscribe();
//		gameStorage.putCard(gameName,"9").subscribe();
//		gameStorage.putCard(gameName,"8").subscribe();
		return "test";
	}
}
