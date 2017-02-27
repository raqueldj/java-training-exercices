package io.robusta.birthday.implementations;

import io.robusta.birthday.interfaces.IGeneration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas Zozol on 04/10/2016.
 */
public class Generation implements IGeneration {

	List<PeopleCollection> collections;

	public Generation() {

	}

	public Generation(int n, int collectionSize) {
		this.collections = createAllRandom(n, collectionSize);
	}

	@Override
	public PeopleCollection createRandom(int size) {
		return new PeopleCollection(size);
	}

	@Override
	public List<PeopleCollection> createAllRandom(int n, int size) {
		// call n times createRandom(size)
		List<PeopleCollection> randomList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			randomList.add(this.createRandom(size));
		}
		return randomList;
	}

	@Override
	public List<PeopleCollection> getPeopleCollections() {
		return this.collections;
	}

	@Override
	public int getNumberOfCollectionsThatHasTwoPeopleWithSameBirthday() {
		int compteur = 0;

		for (PeopleCollection i : getPeopleCollections()) {
			if (i.hasSame() == true) {
				compteur++;
			}
		}
		return compteur;
	}

	@Override
	public boolean isLessThan50() {
		if(getNumberOfCollectionsThatHasTwoPeopleWithSameBirthday()<50){
			return true;
		}
		return false;
	}

}
