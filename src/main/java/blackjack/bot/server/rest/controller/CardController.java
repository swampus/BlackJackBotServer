package blackjack.bot.server.rest.controller;

import blackjack.bot.server.rest.model.SingleCardOutRequest;
import blackjack.bot.server.service.CardManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardManagementService cardManagementService;

	@RequestMapping(value = "/card/add}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void cardOut(@RequestBody SingleCardOutRequest singleCardOutRequest) {
		cardManagementService.addCard(singleCardOutRequest.getGameName(),
				singleCardOutRequest.getGameToken(), singleCardOutRequest.getCardValue()).toBlocking().first();
	}


}
