package blackjack.bot.server.service.strategy.card.calculation;


import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.storage.model.Card;
import com.google.common.annotations.VisibleForTesting;
import rx.Observable;
import rx.math.operators.OperatorSum;

public class CustomCardCalculationSystem implements CardCalculationSystem {

	private Double two;
	private Double tree;
	private Double four;
	private Double five;
	private Double six;
	private Double seven;
	private Double eight;
	private Double nine;
	private Double ten;
	private Double ace;

	public CustomCardCalculationSystem() {
		throw new RuntimeException("Can not use custom cardSystem without configuration!");
	}

	public CustomCardCalculationSystem(
			Double two, Double tree, Double four, Double five, Double six, Double seven, Double eight,
			Double nine, Double ten, Double ace) {

		this.two = two;
		this.tree = tree;
		this.four = four;
		this.five = five;
		this.six = six;
		this.seven = seven;
		this.eight = eight;
		this.nine = nine;
		this.ten = ten;
		this.ace = ace;
	}


	@VisibleForTesting
	Double getCardValueInSystem(Card card) {
		switch (Cards.valueOf(card.getCard())) {
			case C_2:
				return two;
			case C_3:
				return tree;
			case C_4:
				return four;
			case C_5:
				return five;
			case C_6:
				return six;
			case C_7:
				return seven;
			case C_8:
				return eight;
			case C_9:
				return nine;
			case C_T:
				return ten;
			case C_A:
				return ace;
			default:
				throw new RuntimeException("Something broken in card analyze, wrong card was added!");
		}
	}

	@Override
	public Observable<Double> calculate(Observable<Card> cards) {
		return OperatorSum.sumDoubles(cards.map(this::getCardValueInSystem));
	}
}
