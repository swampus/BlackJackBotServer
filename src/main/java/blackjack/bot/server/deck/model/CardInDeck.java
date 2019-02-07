package blackjack.bot.server.deck.model;

import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.rest.model.ennum.CardsColour;
import com.google.common.base.Objects;

public class CardInDeck {
    private Cards value;
    private CardsColour color;

    public CardInDeck(Cards value, CardsColour color) {
        this.value = value;
        this.color = color;
    }

    public Cards getValue() {
        return value;
    }

    public CardsColour getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInDeck that = (CardInDeck) o;
        return value == that.value &&
                color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value, color);
    }

    @Override
    public String toString() {
        return "CardInDeck{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }
}
