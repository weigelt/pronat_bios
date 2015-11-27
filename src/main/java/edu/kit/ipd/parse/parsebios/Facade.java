package edu.kit.ipd.parse.parsebios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bios.chunker.Chunker;
import bios.chunker.ChunkerConstants;
import bios.common.PosToken;

public class Facade {

	private static final Logger logger = LoggerFactory.getLogger(Facade.class);
	private static final String resourceModelPath = "/model/conll.paum.cs.model";

	public void parseFile(File file) throws Exception {
		Chunker chk = new Chunker("target/classes/model", "conll.paum.cs.model", ChunkerConstants.PAUM, true, false);
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		chk.test(br, System.out);
	}

	public String[] parse(String[] words, String[] pos) {

		List<String> resList = new ArrayList<String>() {
			{
				add(resourceModelPath);
				add(resourceModelPath + ".features");
				add(resourceModelPath + ".labels");
			}
		};

		try {
			if (words != null && pos != null && words.length == pos.length) {

				Path tempDirPath = copyResourcesToTempDir(resList);
				Chunker chk = new Chunker(tempDirPath.toString(), "conll.paum.cs.model", ChunkerConstants.PAUM, true, false);
				logger.info("Chunker is {}", chk.toString());
				ArrayList<PosToken> list = new ArrayList<PosToken>();
				for (int i = 0; i < words.length; i++) {
					PosToken tmp = new PosToken(words[i], pos[i], -1, -1);
					list.add(tmp);
				}
				return chk.predict(list, 0, list.size());
			}
		} catch (Exception e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private Path copyResourcesToTempDir(List<String> resList) throws IOException, URISyntaxException {
		Path tempDirPath = Files.createTempDirectory(null);
		URL resourceUrl;
		InputStream resourceIS;
		Path resourcePath;

		for (String res : resList) {
			resourceUrl = getClass().getResource(res);
			resourceIS = getClass().getResourceAsStream(res);
			resourcePath = Paths.get(getClass().getResource(res).toURI());
			Files.copy(resourceIS, tempDirPath.resolve(resourcePath.getFileName()));
		}
		return tempDirPath;
	}
}
