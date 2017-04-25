package blackjack.bot.server.service.strategy.card.calculation;

import org.springframework.stereotype.Component;

@Component
public class HalvesSystem extends CustomCardCalculationSystem implements CardCalculationSystem {
	//+0.5 +1 +1 +1.5 +1 +0.5 0 -0.5 -1 -1
	public HalvesSystem() {
		super(0.5, 1.0, 1.0, 1.5, 1.0, 0.5, 0.0, -0.5, -1.0, -1.0);
	}
}
