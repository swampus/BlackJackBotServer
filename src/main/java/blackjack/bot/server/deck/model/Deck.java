package blackjack.bot.server.deck.model;

import java.util.Collections;
import java.util.List;

public class Deck {

    private Long id;
    private List<CardInDeck> cardsInDecks;

    public Deck(Long id, List<CardInDeck> cardsInDecks) {
        this.id = id;
        this.cardsInDecks = cardsInDecks;
    }

    public void shuffleDeck() {
        Collections.shuffle(cardsInDecks);
    }

    public CardInDeck getTopCard() {
        int lastElementIndex = cardsInDecks.size() - 1;
        return cardsInDecks.get(lastElementIndex);
    }

    /**
     * Get card and put on table
     */
    public CardInDeck getTopCardAndRemoveItFromDeck() {
        int lastElementIndex = cardsInDecks.size() - 1;
        CardInDeck cardInDeck = cardsInDecks.get(lastElementIndex);
        cardsInDecks.remove(lastElementIndex);
        return cardInDeck;
    }

    public int cardLeftInDeck(){
        return cardsInDecks.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", cardsInDecks=" + cardsInDecks +
                '}';
    }
}
