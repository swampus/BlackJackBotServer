package blackjack.bot.server.rest.factory;

import blackjack.bot.server.exception.UnknownCalculationSystem;
import blackjack.bot.server.service.strategy.card.calculation.CardCalculationSystem;
import blackjack.bot.server.service.strategy.card.calculation.HalvesSystem;
import blackjack.bot.server.service.strategy.card.calculation.KoSystem;
import blackjack.bot.server.service.strategy.card.calculation.OmegaTwoSystem;
import blackjack.bot.server.service.strategy.card.calculation.ZenCountSystem;
import org.springframework.stereotype.Service;

@Service
public class CalculationSystemFactory {
	public CardCalculationSystem createCardCalculationSystem(String systemName) {
		switch (systemName) {
			case "Halves":
				return new HalvesSystem();
			case "Ko":
				return new KoSystem();
			case "OmegaTwo":
				return new OmegaTwoSystem();
			case "ZenCount":
				return new ZenCountSystem();
			default:
				throw new UnknownCalculationSystem(systemName);
		}
	}
}
