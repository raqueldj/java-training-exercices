package io.robusta.birthday.implementations;

import io.robusta.birthday.interfaces.IGenerationThreshold;
import io.robusta.birthday.interfaces.IPeopleCollection;

import java.util.ArrayList;

/**
 * Created by Nicolas Zozol on 04/10/2016.
 */
public class GenerationThreshold implements IGenerationThreshold {

	public GenerationThreshold() {

	}

	@Override
	public int getSmallNumber() {
		return 0;
	}

	@Override
	public int getBigNumber() {
		return 0;
	}

	@Override
	public float calculateProbabilityOfSame(int size) {
		float probabilite;
		probabilite = getNumberOfCollectionsThatHasTwoPeopleWithSameBirthday()
		return 0;
	}

	@Override
	public int findSmallestNumberOfPeopleRequiredToHave50() {
		return 0;
	}
}
