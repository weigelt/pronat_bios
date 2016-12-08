package edu.kit.ipd.parse.parsebios;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class SimpleFacadeTest {

	@Test
	public void test() {
		final String[] words = { "My", "Name", "is", "Markus" };
		final String[] pos = { "PRP$", "NN", "VBZ", "NNP" };
		final String[] exp = { "B-NP", "I-NP", "B-VP", "B-NP" };
		final Facade a = new Facade();
		final String[] parse = a.parse(words, pos);
		Assert.assertArrayEquals(exp, parse);

	}

	@Test
	public void testADVP() {
		final String[] words = { "If", "the", "black", "dog", "barks", "loudly", "go" };
		final String[] pos = { "IN", "DT", "JJ", "NN", "VBZ", "RB", "VB" };
		final String[] exp = { "B-SBAR", "B-NP", "I-NP", "I-NP", "B-VP", "I-VP", "I-VP" };
		final Facade a = new Facade();
		final String[] parse = a.parse(words, pos);
		System.out.println(Arrays.deepToString(words));
		System.out.println(Arrays.deepToString(parse));
		Assert.assertArrayEquals(exp, parse);

	}

	@Test
	public void testADVP2() {
		final String[] words = { "Open", "the", "door", "next", "to", "the", "dishwasher" };
		final String[] pos = { "VB", "DT", "NN", "JJ", "TO", "DT", "NN" };
		final String[] exp = { "B-VP", "B-NP", "I-NP", "B-NP", "B-PP", "B-NP", "I-NP" };
		final Facade a = new Facade();
		final String[] parse = a.parse(words, pos);
		System.out.println(Arrays.deepToString(words));
		System.out.println(Arrays.deepToString(parse));
		Assert.assertArrayEquals(exp, parse);

	}
}