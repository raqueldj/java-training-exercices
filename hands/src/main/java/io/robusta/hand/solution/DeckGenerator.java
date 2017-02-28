package io.robusta.hand.solution;

import io.robusta.hand.Card;
import io.robusta.hand.CardColor;
import io.robusta.hand.interfaces.IDeckGenerator;

public class DeckGenerator implements IDeckGenerator {

	@Override
	public Deck generate() {
		Deck deck = new Deck();
		// fill the deck with cards
		// Probably use the good modulo
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 13; j++) {
				Card card = new Card(j, CardColor.getByValue(i));
				deck.add(card);
			}
		}

		return deck;
	}

}
