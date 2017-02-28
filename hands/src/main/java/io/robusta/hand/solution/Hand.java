package io.robusta.hand.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import io.robusta.hand.Card;
import io.robusta.hand.CardColor;
import io.robusta.hand.HandClassifier;
import io.robusta.hand.HandValue;
import io.robusta.hand.interfaces.IDeck;
import io.robusta.hand.interfaces.IHand;
import io.robusta.hand.interfaces.IHandResolver;

public class Hand extends TreeSet<Card> implements IHand {

	private static final long serialVersionUID = 7824823998655881611L;

	@Override
	public Set<Card> changeCards(IDeck deck, Set<Card> cards) {
		// For exemple remove three cards from this hand
		// , and get 3 new ones from the Deck
		// returns the new given cards
		return null;
	}

	@Override
	public boolean beats(IHand villain) {
		if (this.getValue().compareTo(villain.getValue()) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public IHand getHand() {
		return this;
	}

	@Override
	public HandClassifier getClassifier() {

		return this.getValue().getClassifier();
	}

	@Override
	public boolean isStraight() {

		List<Card> cards = new ArrayList<>(this);
		for (int i = 0; i < 4; i++) {
			if (cards.get(i + 1).getValue() != cards.get(i).getValue() + 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isFlush() {

		CardColor c = this.first().getColor();
		for (Card card : this)
			if (card.getColor() != c) {
				return false;
			}
		return true;
	}

	/**
	 * Returns number of identical cards 5s5cAd2s3s has two cardValue of 5
	 */
	@Override
	public int number(int cardValue) {
		int result = 0;
		for (Card current : this) {
			if (current.getValue() == cardValue) {
				result++;
			}
		}
		return result;
	}

	/**
	 * The fundamental map Check the tests and README to understand
	 */
	@Override
	public HashMap<Integer, List<Card>> group() {
		HashMap<Integer, List<Card>> map = new HashMap<>();

		ArrayList<Card> list = null;

		for (Card c : this) {
			// fill the map
			if (map.get(c.getValue()) == null) {
				list = new ArrayList<Card>();
				list.add(c);
				map.put(c.getValue(), list);
			} else {
				list.add(c);
				map.put(c.getValue(), list);
			}

		}

		return map;
	}

	// different states of the hand
	int mainValue;
	int tripsValue;
	int pairValue;
	int secondValue;
	TreeSet<Card> remainings;

	/**
	 * return all single cards not used to build the classifier
	 * 
	 * @param map
	 * @return
	 */
	TreeSet<Card> getGroupRemainingsCard(Map<Integer, List<Card>> map) {
		TreeSet<Card> groupRemaining = new TreeSet<>();
		// May be adapted at the end of project:
		// if straight or flush : return empty
		// If High card, return 4 cards

		for (List<Card> group : map.values()) {
			if (group.size() == 1) {
				groupRemaining.add(group.get(0));
			}
		}
		return groupRemaining;
	}

	@Override
	public boolean isPair() {
		Map<Integer, List<Card>> group = group();

		if (group.size() == 4) {
			for (List<Card> rows : group.values()) {
				if (rows.size() == 2) {
					mainValue = rows.get(0).getValue();
				}
			}

			return true;
		}
		return false;

	}

	@Override
	public boolean isDoublePair() {

		Map<Integer, List<Card>> group = group();

		if (group.size() == 3) {
			for (List<Card> rows : group.values()) {
				if (rows.size() == 2) {
					int a = rows.get(0).getValue();
					int b = rows.get(1).getValue();
					if (a < b) {
						mainValue = b;
						secondValue = a;
					} else {
						mainValue = a;
						secondValue = b;
					}
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean isHighCard() {
		Map<Integer, List<Card>> group = group();

		if (group.size() == 5) {
			if (!this.isFlush() && !this.isStraight()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isTrips() {

		Map<Integer, List<Card>> group = group();

		if (group.size() == 3) {
			for (List<Card> rows : group.values()) {
				if (rows.size() == 3) {
					mainValue = rows.get(0).getValue();
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean isFourOfAKind() {

		Map<Integer, List<Card>> group = group();

		if (group.size() == 2) {
			for (List<Card> rows : group.values()) {
				if (rows.size() == 4) {
					mainValue = rows.get(0).getValue();
				}
			}

			return true;
		}
		return false;

	}

	@Override
	public boolean isFull() {

		Map<Integer, List<Card>> group = group();

		if (group.size() == 2) {
			for (List<Card> rows : group.values()) {
				if (rows.size() == 3) {
					mainValue = rows.get(0).getValue();
					if (rows.size() == 2) {
						secondValue = rows.get(0).getValue();
					}
				}

			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isStraightFlush() {
		if (this.isFlush() && this.isStraight()) {
			return true;
		}
		return false;
	}

	@Override
	public HandValue getValue() {
		HandValue handValue = new HandValue();

		if (this.isStraight()) {
			handValue.setClassifier(HandClassifier.STRAIGHT);
			handValue.setLevelValue(this.last().getValue());
		}

		if (this.isFlush()) {
			handValue.setClassifier(HandClassifier.FLUSH);
			handValue.setOtherCards(this.getGroupRemainingsCard(this.group()));
		}

		if (this.isStraightFlush()) {
			handValue.setClassifier(HandClassifier.STRAIGHT_FLUSH);
			handValue.setLevelValue(this.last().getValue());
		}

		if (this.isPair()) {
			handValue.setClassifier(HandClassifier.PAIR);
			handValue.setLevelValue(this.mainValue);
			handValue.setOtherCards(this.getGroupRemainingsCard(this.group()));
		}

		if (this.isDoublePair()) {
			handValue.setClassifier(HandClassifier.TWO_PAIR);
			handValue.setLevelValue(this.mainValue);
			handValue.setSecondLevel(this.secondValue);
			handValue.setOtherCards(this.getGroupRemainingsCard(this.group()));
		}

		if (this.isHighCard()) {
			handValue.setClassifier(HandClassifier.HIGH_CARD);
			handValue.setLevelValue(this.last().getValue());
			handValue.setOtherCards(this.getGroupRemainingsCard(this.group()));
		}

		if (this.isFull()) {
			handValue.setClassifier(HandClassifier.FULL);
			handValue.setLevelValue(this.mainValue);
			handValue.setSecondLevel(this.secondValue);
			// handValue.setOtherCards(this.getGroupRemainingsCard(this.group()));
		}

		// Exemple for FourOfAKind ; // do for all classifiers
		if (this.isFourOfAKind()) {
			handValue.setClassifier(HandClassifier.FOUR_OF_A_KIND);
			handValue.setLevelValue(this.mainValue);
			handValue.setOtherCards(this.getGroupRemainingsCard(this.group())); // or
																				// this.getRemainings()
		}

		return handValue;
	}

	@Override
	public boolean hasCardValue(int level) {

		for (Card c : this) {
			if(c.getValue() == level){
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasAce() {
		return false;
	}

	@Override
	public int highestValue() {
		// ace might be the highest value
		return 0;
	}

	@Override
	public int compareTo(IHandResolver o) {

		return this.getValue().compareTo(o.getValue());
	}

}
