package edu.kit.ipd.parse.parsebios;

import org.junit.Assert;
import org.junit.Test;

import edu.kit.ipd.parse.parsebios.Facade;

public class simpleFacadeTest {

	@Test
	public void test() {
		String[] words = { "My", "Name", "is", "Markus" };
		String[] pos = { "PRP$", "NN", "VBZ", "NNP" };
		String[] exp = { "B-NP", "I-NP", "B-VP", "B-NP" };
		Facade a = new Facade();
		String[] parse = a.parse(words, pos);
		Assert.assertArrayEquals(exp, parse);

	}
}