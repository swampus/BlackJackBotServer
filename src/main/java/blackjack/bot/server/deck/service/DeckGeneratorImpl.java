package blackjack.bot.server.deck.service;

import blackjack.bot.server.deck.model.CardInDeck;
import blackjack.bot.server.deck.model.Deck;
import blackjack.bot.server.rest.model.ennum.Cards;
import blackjack.bot.server.rest.model.ennum.CardsColour;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DeckGeneratorImpl implements DeckGenerator {

    @Override
    public Deck generateDeckAndShuffle(Long id) {
        Deck deck = new Deck(id, createAllCardsForDeck());
        deck.shuffleDeck();
        return deck;
    }

    @Override
    public Deck generateNewDeck(Long id) {
        return new Deck(id, createAllCardsForDeck());
    }

    private List<CardInDeck> createAllCardsForDeck() {
        List<CardInDeck> cardsInDeck = new ArrayList<>();
        Stream.of(Cards.values()).forEachOrdered((Cards card)
                -> Stream.of(CardsColour.values()).forEachOrdered((CardsColour color)
                -> cardsInDeck.add(new CardInDeck(card, color))));
        return cardsInDeck;
    }

}
