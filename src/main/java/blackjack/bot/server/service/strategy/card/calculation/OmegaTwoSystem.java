package blackjack.bot.server.service.strategy.card.calculation;

import org.springframework.stereotype.Component;

@Component
public class OmegaTwoSystem extends CustomCardCalculationSystem implements CardCalculationSystem {
	//+1 +1 +2 +2 +2 +1 0 −1 −2 0 2
	public OmegaTwoSystem() {
		super(1.0, 1.0, 2.0, 2.0, 2.0, 1.0, 0.0, -1.0, -2.0, 0.0);
	}

}
