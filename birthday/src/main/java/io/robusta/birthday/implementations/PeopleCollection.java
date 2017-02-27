package io.robusta.birthday.implementations;

import io.robusta.birthday.interfaces.IPeopleCollection;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicolas Zozol on 04/10/2016.
 */
public class PeopleCollection extends ArrayList<People> implements IPeopleCollection<People> {

	People people;
	Random random;
	int date;

	public PeopleCollection() {

	}

	public PeopleCollection(int size) {
		super(size);
		for (int i = 0; i < size; i++) {
			people = new People();
			random = new Random();
			date = random.nextInt(365) + 1;
			people.setBirthday(date);
			this.add(people);
		}
	}

	@Override
	public boolean hasSame() {

		/*
		 * return true if two people of the same collection has same birthday
		 */

		for (int i = 0; i < this.size(); i++) {
			for (int j = i+1; j < this.size(); j++) {
				if (this.get(i).equals(this.get(j))) {
					return true;
				}
			}
		}

		return false;
	}

}
