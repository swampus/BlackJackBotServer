package blackjack.bot.server.service.strategy.card.calculation.factory;

import blackjack.bot.server.exception.UnknownCalculationSystem;
import blackjack.bot.server.service.strategy.card.calculation.CardCalculationSystem;
import blackjack.bot.server.service.strategy.card.calculation.HalvesSystem;
import blackjack.bot.server.service.strategy.card.calculation.KoSystem;
import blackjack.bot.server.service.strategy.card.calculation.OmegaTwoSystem;
import blackjack.bot.server.service.strategy.card.calculation.ZenCountSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationSystemFactory {

	@Autowired
	private HalvesSystem halvesSystem;

	@Autowired
	private KoSystem koSystem;

	@Autowired
	private OmegaTwoSystem omegaTwoSystem;

	@Autowired
	private ZenCountSystem zenCountSystem;

	public CardCalculationSystem createCardCalculationSystem(String systemName) {
		switch (systemName) {
			case "Halves":
				return halvesSystem;
			case "Ko":
				return koSystem;
			case "OmegaTwo":
				return omegaTwoSystem;
			case "ZenCount":
				return zenCountSystem;
			default:
				throw new UnknownCalculationSystem(systemName);
		}
	}
}
