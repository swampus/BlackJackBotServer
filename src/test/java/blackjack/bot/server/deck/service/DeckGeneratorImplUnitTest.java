package blackjack.bot.server.deck.service;

import blackjack.bot.server.deck.model.CardInDeck;
import blackjack.bot.server.deck.model.Deck;
import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.rest.model.ennum.CardsColour;
import org.junit.Test;

import static org.junit.Assert.*;


public class DeckGeneratorImplUnitTest {

    private DeckGeneratorImpl deckGenerator = new DeckGeneratorImpl();

    @Test
    public void generateNewDeck() {
        Deck deck = deckGenerator.generateNewDeck((long) 1);
        assertEquals(52, deck.cardLeftInDeck());

        CardInDeck topCard = deck.getTopCard();
        assertEquals(new CardInDeck(Cards.C_2, CardsColour.SPADES), topCard);

        deck.getTopCardAndRemoveItFromDeck();
        deck.getTopCardAndRemoveItFromDeck();

        topCard = deck.getTopCard();
        assertEquals(new CardInDeck(Cards.C_2, CardsColour.DIAMONDS), topCard);

        assertEquals(50, deck.cardLeftInDeck());
    }
}