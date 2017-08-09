package blackjack.bot.server.rest.controller;

import blackjack.bot.server.rest.model.AllGamesByCasinoRequest;
import blackjack.bot.server.rest.model.DeckShuffleRequest;
import blackjack.bot.server.rest.model.GameFinishRequest;
import blackjack.bot.server.rest.model.GameStateResponse;
import blackjack.bot.server.rest.model.GetGameStateRequest;
import blackjack.bot.server.service.CardManagementService;
import blackjack.bot.server.service.GameManagementService;
import blackjack.bot.server.service.strategy.BaseStrategy;
import blackjack.bot.server.service.strategy.card.calculation.factory.CalculationSystemFactory;
import blackjack.bot.server.service.strategy.model.NextAction;
import blackjack.bot.server.storage.model.Game;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameManagementService gameManagementService;

	@Autowired
	private CardManagementService cardManagementService;

	@Autowired
	private CalculationSystemFactory calculationSystemFactory;

	@Autowired
	private BaseStrategy baseStrategy;

	@RequestMapping(value = "/game/start}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)

	@ResponseBody
	public void startGame() {
	}

	@RequestMapping(value = "/game/state}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GameStateResponse getGameState(@RequestBody GetGameStateRequest gameStateRequest) {
		return new GameStateResponse(gameStateRequest.getGameName(),
				calculationSystemFactory
						.createCardCalculationSystem(gameStateRequest.getSystem())
						.calculate(cardManagementService.getAllCardsByGame(gameStateRequest.getGameName(),
								gameStateRequest.getGameName())).toBlocking().first());
	}


	@RequestMapping(value = "/game/shuffle}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void shuffleGame(DeckShuffleRequest deckShuffleRequest) {
		gameManagementService
				.shuffleGame(deckShuffleRequest.getGameName(), deckShuffleRequest.getGameToken());
	}

	@RequestMapping(value = "/game/stop}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void stopGame(GameFinishRequest gameFinishRequest) {
		gameManagementService
				.stopGame(gameFinishRequest.getGameName(), gameFinishRequest.getGameToken())
				.toBlocking().first();
	}


	@RequestMapping(value = "/game/casino/advantage}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Game> displayAllGamesInCasino(AllGamesByCasinoRequest allGamesByCasinoRequest) {
		return gameManagementService.getAllGamesInCasino(allGamesByCasinoRequest.getCasinoName())
				.toList().toBlocking().single();
	}


	@RequestMapping(value = "/game/casino/advantage}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Game> displayAllGamesWithAdvantageInCasino() {
		return Lists.newArrayList();
	}

	@RequestMapping(value = "/game/casino/base/next}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//replace to POJO request
	public NextAction getNextTurnInGameByStrategy(String gameName,
	                                              String token,
	                                              String strategy,
	                                              Integer dealerCardValue,
	                                              Integer playerCardValue) {

		Double gameCount = calculationSystemFactory
				.createCardCalculationSystem(strategy)
				.calculate(cardManagementService.getAllCardsByGame(gameName, token)).toBlocking().first();

		return baseStrategy.whatNext(dealerCardValue, playerCardValue, gameCount);
	}

}
