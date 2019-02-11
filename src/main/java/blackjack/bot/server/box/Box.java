package blackjack.bot.server.box;

import blackjack.bot.server.box.eenum.BoxStatus;
import blackjack.bot.server.deck.model.CardInDeck;
import blackjack.bot.server.exception.CanNotDrawCardToBoxException;
import blackjack.bot.server.exception.CanNotSplitBoxException;

import java.util.ArrayList;
import java.util.List;

public class Box {

    private static final int FIRST_CARD = 0;
    private static final int SECOND_CARD = 1;
    public static final int SUB_ID_IDENTIFICATOR = 101;

    private Long id;
    private BoxStatus status;
    private List<CardInDeck> cards;

    public Box(Long id, BoxStatus status) {
        this.id = id;
        this.status = status;
        cards = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BoxStatus getStatus() {
        return status;
    }

    public void setStatus(BoxStatus status) {
        this.status = status;
    }

    public List<CardInDeck> getCards() {
        return cards;
    }

    public void addCardToBox(CardInDeck cardInDeck) {
        if (status != BoxStatus.ACTIVE) {
            throw new CanNotDrawCardToBoxException("Box: " + id + "Is not ACTIVE(" + status + ")");
        }
        cards.add(cardInDeck);
    }

    public Box splitAndCreateNewBox() {
        if ((cards.size() != 2) || !cards.get(FIRST_CARD).equals(cards.get(SECOND_CARD))) {
            throw new CanNotSplitBoxException("Wrong cards for split (not equals or not start)");
        }
        CardInDeck secondCard = cards.get(SECOND_CARD);
        cards.remove(secondCard);
        Box newBox = new Box(createParentBoxId(), BoxStatus.ACTIVE);
        newBox.addCardToBox(secondCard);
        return newBox;
    }

    private long createParentBoxId() {
        return id + SUB_ID_IDENTIFICATOR;
    }
}
