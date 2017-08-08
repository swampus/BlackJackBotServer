package blackjack.bot.server.rest.controller;

import blackjack.bot.server.annotation.Restricted;
import blackjack.bot.server.rest.model.NewCasinoRequest;
import blackjack.bot.server.service.CasinoManagementService;
import blackjack.bot.server.storage.model.Casino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Restricted
@RestController
@RequestMapping("/casino")
public class CasinoController {

	@Autowired
	private CasinoManagementService casinoManagementService;

	@RequestMapping(value = "/casino/add}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void addCasino(NewCasinoRequest newCasinoRequest){
		Casino casino = new Casino(newCasinoRequest.getCasinoName(),newCasinoRequest.getCasinoName());
		casinoManagementService.addCasino(casino).toBlocking().first();
	}
}
