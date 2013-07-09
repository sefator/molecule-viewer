package cz.sefware.jchem.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;

public class SimpleMoleculeService {

	private static final String INFO_EXTENSION = ".molinfo";
	private static final String BASE_DIRECTORY = "files/";

	public List<MoleculeInfo> getMoleculeInfos() {
		File directory = new File(BASE_DIRECTORY);
		File[] files = directory.listFiles(new FilenameFilter() {

			public boolean accept(File file, String filename) {
				String lowerName = filename.toLowerCase();
				if (lowerName.endsWith(INFO_EXTENSION)) {
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

	public void saveMolecule(byte[] bytes, Molecule molecule) {
		FileOutputStream output = null;
		MoleculeInfo info = new MoleculeInfo();
		info.setFilename(String.valueOf(System.nanoTime()));
		info.setName(molecule.getFormula());
		info.setFormat(molecule.getInputFormat());
		try {
			output = new FileOutputStream(new File(BASE_DIRECTORY
					+ File.separator + info.getFilename()));
			IOUtils.write(bytes, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			IOUtils.closeQuietly(output);
		}

		try {
			output = new FileOutputStream(new File(BASE_DIRECTORY
					+ File.separator + info.getFilename() + INFO_EXTENSION));
			ObjectMapper om = new ObjectMapper();
			om.writeValue(output, info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			IOUtils.closeQuietly(output);
		}

	}

}
