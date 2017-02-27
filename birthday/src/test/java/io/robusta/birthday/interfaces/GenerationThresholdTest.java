package io.robusta.birthday.interfaces;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.robusta.birthday.implementations.GenerationThreshold;

public class GenerationThresholdTest {
	GenerationThreshold generationThreshold;

	@Test
	public void findSmallestNumberOfPeopleRequiredToHave50() throws Exception {

		IGenerationThreshold generationThreshold = new GenerationThreshold();
		assertTrue(generationThreshold.findSmallestNumberOfPeopleRequiredToHave50() == 23);
	}

	@Test
	public void calculateProbabilityOfSame(int size) throws Exception {
		generationThreshold = new GenerationThreshold();
		assertTrue(generationThreshold.calculateProbabilityOfSame(3)<0.3);
		
		assertTrue(generationThreshold.calculateProbabilityOfSame(100)>0.9);
	}

}
