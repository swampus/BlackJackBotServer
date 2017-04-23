package blackjack.bot.server.service.strategy.card.calculation;

public class ZenCountSystem extends CustomCardCalculationSystem implements CardCalculationSystem {
	//+1 +1 +2 +2 +2 +1 0 0 −2 −1
	public ZenCountSystem() {
		super(1.0, 1.0, 2.0, 2.0, 2.0, 1.0, 0.0, 0.0, -2.0, -1.0);
	}
}
