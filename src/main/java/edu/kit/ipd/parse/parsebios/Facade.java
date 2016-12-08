package edu.kit.ipd.parse.parsebios;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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

	//	public void parseFile(File file) throws Exception {
	//		Chunker chk = new Chunker("target/classes/model", "conll.paum.cs.model", ChunkerConstants.PAUM, true, false);
	//		FileReader fileReader = new FileReader(file);
	//		BufferedReader br = new BufferedReader(fileReader);
	//		chk.test(br, System.out);
	//	}

	public String[] parse(String[] words, String[] pos) {
		logger.info("Running Chunker");
		final List<String> resList = new ArrayList<String>() {
			{
				add(resourceModelPath);
				add(resourceModelPath + ".features");
				add(resourceModelPath + ".labels");
			}
		};

		try {
			if (words != null && pos != null && words.length == pos.length) {

				final Path tempDirPath = copyResourcesToTempDir(resList);
				final Chunker chk = new Chunker(tempDirPath.toString(), "conll.paum.cs.model", ChunkerConstants.PAUM, true, false);
				final ArrayList<PosToken> list = new ArrayList<PosToken>();
				for (int i = 0; i < words.length; i++) {
					final PosToken tmp = new PosToken(words[i], pos[i], -1, -1);
					list.add(tmp);
				}
				return chk.predict(list, 0, list.size());
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Path copyResourcesToTempDir(List<String> resList) throws IOException, URISyntaxException {
		final Path tempDirPath = Files.createTempDirectory(null);
		InputStream resourceIS;
		Path destPath;
		for (final String res : resList) {
			resourceIS = getClass().getResourceAsStream(res);
			destPath = Paths.get(tempDirPath.toString() + res.substring(res.lastIndexOf("/")));
			Files.copy(resourceIS, destPath);
		}
		return tempDirPath;
	}
}
