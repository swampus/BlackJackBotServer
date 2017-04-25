package blackjack.bot.server.service.strategy.card.calculation;

import org.springframework.stereotype.Component;

@Component
public class KoSystem extends CustomCardCalculationSystem implements CardCalculationSystem {
	//+1 +1 +1 +1 +1 +1 0 0 −1 −1
	public KoSystem() {
		super(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, -1.0, -1.0);
	}
}
