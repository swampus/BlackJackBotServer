package blackjack.bot.server.deck.service;

import blackjack.bot.server.deck.model.Deck;

public interface DeckGenerator {

    Deck generateDeckAndShuffle(Long id);

    Deck generateNewDeck(Long id);

}
