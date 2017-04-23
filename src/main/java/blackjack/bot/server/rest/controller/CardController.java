package blackjack.bot.server.rest.controller;

import blackjack.bot.server.rest.factory.CalculationSystemFactory;
import blackjack.bot.server.rest.model.DeckShuffleRequest;
import blackjack.bot.server.rest.model.GameStateResponse;
import blackjack.bot.server.rest.model.GetGameStateRequest;
import blackjack.bot.server.rest.model.SingleCardOutRequest;
import blackjack.bot.server.service.CardManagementService;
import blackjack.bot.server.service.GameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardManagementService cardManagementService;

	@Autowired
	private GameManagementService gameManagementService;

	@Autowired
	private CalculationSystemFactory calculationSystemFactory;

	public GameStateResponse getGameState(GetGameStateRequest gameStateRequest) {
		return new GameStateResponse(gameStateRequest.getGameName(),
				calculationSystemFactory
						.createCardCalculationSystem(gameStateRequest.getSystem())
						.calculate(cardManagementService.getAllCardsByGame(gameStateRequest.getGameName(),
								gameStateRequest.getGameName())).toBlocking().first());
	}

	public void cardOut(SingleCardOutRequest singleCardOutRequest) {
		cardManagementService.addCard(singleCardOutRequest.getGameName(),
				singleCardOutRequest.getGameToken(), singleCardOutRequest.getCardValue()).subscribe();
	}

	public void shuffleGame(DeckShuffleRequest deckShuffleRequest) {
		gameManagementService
				.shuffleGame(deckShuffleRequest.getGameName(), deckShuffleRequest.getGameToken());
	}

}
