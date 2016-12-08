package edu.kit.ipd.parse.parsebios;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class SimpleFacadeTest {

	@Test
	public void test() {
		String[] words = { "My", "Name", "is", "Markus" };
		String[] pos = { "PRP$", "NN", "VBZ", "NNP" };
		String[] exp = { "B-NP", "I-NP", "B-VP", "B-NP" };
		Facade a = new Facade();
		String[] parse = a.parse(words, pos);
		Assert.assertArrayEquals(exp, parse);

	}

	@Test
	public void testADVP() {
		String[] words = { "If", "the", "black", "dog", "barks", "loudly", "go" };
		String[] pos = { "IN", "DT", "JJ", "NN", "VBZ", "RB", "VB" };
		String[] exp = { "B-SBAR", "B-NP", "I-NP", "I-NP", "B-VP", "B-ADVP", "B-VP" };
		Facade a = new Facade();
		String[] parse = a.parse(words, pos);
		Assert.assertArrayEquals(exp, parse);

	}

	@Test
	public void testADVP2() {
		String[] words = { "Open", "the", "door", "next", "to", "the", "dishwasher." };
		String[] pos = { "VB", "DT", "NN", "JJ", "TO", "DT", "NN" };
		String[] exp = { "B-SBAR", "B-NP", "I-NP", "I-NP", "B-VP", "B-ADVP", "B-VP" };
		Facade a = new Facade();
		String[] parse = a.parse(words, pos);
		System.out.println(Arrays.deepToString(words));
		System.out.println(Arrays.deepToString(parse));
		Assert.assertArrayEquals(exp, parse);

	}
}