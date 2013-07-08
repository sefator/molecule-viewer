package cz.sefware.jchem.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import cz.sefware.jchem.model.MoleculeInfo;

public class SimpleMoleculeService {

	private static final String BASE_DIRECTORY = "files/";

	public List<MoleculeInfo> getMoleculeInfos() {
		File directory = new File(BASE_DIRECTORY);
		File[] files = directory.listFiles(new FilenameFilter() {

			public boolean accept(File file, String filename) {
				String lowerName = filename.toLowerCase();
				if (lowerName.endsWith(".molinfo")) {
					return true;
				}
				return false;
			}

		});

		List<MoleculeInfo> output = new ArrayList<MoleculeInfo>(files.length);
		ObjectMapper om = new ObjectMapper();
		for (File f : files) {
			try {
				output.add(om.readValue(f, MoleculeInfo.class));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return output;

	}

	public void saveMolecule(byte[] bytes, MoleculeInfo info) {

	}

}
