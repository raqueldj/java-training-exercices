package io.robusta.hand.solution;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

import io.robusta.hand.Card;
import io.robusta.hand.CardColor;
import io.robusta.hand.interfaces.IDeck;

public class Deck extends LinkedList<Card> implements IDeck{

	
	private static final long serialVersionUID = -4686285366508321800L;
	
	public Deck() {

	}
	
	@Override
	public Card pick() {
		// shuffle;
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.size());
		
		// remove card from deck and returns it
		Card card = this.get(index);
		this.remove(card);
		return card;
	}


	

	@Override
	public TreeSet<Card> pick(int number) {
		// reuse pick()
		TreeSet<Card> cards = new TreeSet<>();
		
		for(int i = 0; i<number; i++){
			Card card = this.pick();
			
			cards.add(card);
			
		}
		return cards;
	}

	@Override
	public Hand giveHand() {
		// A hand is a **5** card TreeSet
		Hand hand = new Hand();
		hand.addAll(pick(5));
		
		return hand;
	}
	
	
	
}
