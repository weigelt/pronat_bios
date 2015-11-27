package edu.kit.ipd.parse.parsebios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import bios.chunker.Chunker;
import bios.chunker.ChunkerConstants;
import bios.common.PosToken;

public class Facade {

	public void parseFile(File file) throws Exception {
		Chunker chk = new Chunker("target/classes/model", "conll.paum.cs.model", ChunkerConstants.PAUM, true, false);
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		chk.test(br, System.out);
	}

	public String[] parse(String[] words, String[] pos) {
		try {
			if (words != null && pos != null && words.length == pos.length) {
				URL resourceUrl = getClass().getResource("/model");
				Path resourcePath = Paths.get(resourceUrl.toURI());
				Chunker chk = new Chunker(resourcePath.toString(), "conll.paum.cs.model", ChunkerConstants.PAUM, true, false);
				ArrayList<PosToken> list = new ArrayList<PosToken>();
				for (int i = 0; i < words.length; i++) {
					PosToken tmp = new PosToken(words[i], pos[i], -1, -1);
					list.add(tmp);
				}
				return chk.predict(list, 0, list.size());
			}
		} catch (Exception e) {
		}
		return null;
	}

}
