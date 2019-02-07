package blackjack.bot.server.rest.model.ennum;

public enum CardsColour {

    CLUBS(1),
    DIAMONDS(2),
    HEARTS(3),
    SPADES(4);

    private final Integer value;

    CardsColour(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
