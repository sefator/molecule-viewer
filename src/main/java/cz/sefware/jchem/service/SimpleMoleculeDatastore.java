package cz.sefware.jchem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chemaxon.formats.MolFormatException;
import chemaxon.formats.MolImporter;
import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;

/**
 * Simple implementation of MoleculeDatastore, using directory for storing
 * molecule files along with MoleculeInfo metadata.
 * 
 * @author jg
 * 
 */
public class SimpleMoleculeDatastore implements MoleculeDatastore {

	public static final String DEFAULT_DIRECTORY = "/tmp/files/";
	public static final String INFO_EXTENSION = ".molinfo";
	private String baseDirectory;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SimpleMoleculeDatastore.class);

	public SimpleMoleculeDatastore(String directory) {
		baseDirectory = directory;
	}

	public SimpleMoleculeDatastore() {
		baseDirectory = DEFAULT_DIRECTORY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cz.sefware.jchem.service.MoleculeDatastore#getMoleculeInfos()
	 */
	@Override
	public List<MoleculeInfo> getMoleculeInfos() {
		LOGGER.debug("Listing all info files in {}", baseDirectory);
		File directory = new File(baseDirectory);
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
				LOGGER.error("Wrong molecule info file!", e);
			}
		}
		return output;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cz.sefware.jchem.service.MoleculeDatastore#saveMolecule(byte[],
	 * chemaxon.struc.Molecule)
	 */
	@Override
	public MoleculeInfo saveMolecule(byte[] bytes, Molecule molecule,
			String originalFilename) {
		FileOutputStream output = null;
		MoleculeInfo info = new MoleculeInfo();
		info.setId(System.nanoTime());
		info.setFilename(String.valueOf(info.getId()));
		info.setName(originalFilename);
		info.setFormula(molecule.getFormula());
		info.setFormat(molecule.getInputFormat());
		try {
			output = new FileOutputStream(new File(baseDirectory
					+ File.separator + info.getFilename()));
			IOUtils.write(bytes, output);
		} catch (IOException e) {
			LOGGER.error("Error writing molecule info.", e);

		} finally {
			IOUtils.closeQuietly(output);
		}

		try {
			output = new FileOutputStream(new File(baseDirectory
					+ File.separator + info.getFilename() + INFO_EXTENSION));
			ObjectMapper om = new ObjectMapper();
			om.writeValue(output, info);
			return info;
		} catch (IOException e) {
			LOGGER.error("Error writing molecule file.", e);

		} finally {
			IOUtils.closeQuietly(output);
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.sefware.jchem.service.MoleculeDatastore#getMolecule(java.lang.Long)
	 */
	@Override
	public Molecule getMolecule(Long id) {
		try {
			return MolImporter.importMol(IOUtils
					.toByteArray(new FileInputStream(new File(baseDirectory
							+ String.valueOf(id)))));
		} catch (MolFormatException e) {
			LOGGER.error("Molecule file is corrupted!", e);
		} catch (IOException e) {
			LOGGER.error("Error reading molecule.", e);
		}
		return null;
	}

}
