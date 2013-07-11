package cz.sefware.jchem.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import chemaxon.formats.MolImporter;
import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;

public class SimpleMoleculeDatastoreTest {

	private static final String TEST_MOL = "benzene.mol";

	private SimpleMoleculeDatastore datastore;
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {

		datastore = new SimpleMoleculeDatastore(folder.getRoot()
				.getAbsolutePath() + File.separator);
	}

	@Test
	public void testSaveMolecule() throws IOException {
		URL url = this.getClass().getClassLoader().getResource(TEST_MOL);
		byte[] bytes = IOUtils.toByteArray(url);
		MoleculeInfo info = datastore.saveMolecule(bytes,
				MolImporter.importMol(bytes), TEST_MOL);
		Assert.assertNotNull(info);
		Assert.assertEquals("C6H6", info.getFormula());
		cleanup(folder.getRoot().getAbsolutePath() + File.separator
				+ info.getFilename());

	}

	@Test
	public void testGetMoleculeInfos() throws IOException {
		// add one molecule to store
		URL url = this.getClass().getClassLoader().getResource(TEST_MOL);
		byte[] bytes = IOUtils.toByteArray(url);
		MoleculeInfo info = datastore.saveMolecule(bytes,
				MolImporter.importMol(bytes), TEST_MOL);

		// check if there is only one
		Assert.assertEquals(1, datastore.getMoleculeInfos().size());
		cleanup(folder.getRoot().getAbsolutePath() + File.separator
				+ info.getFilename());
	}

	@Test
	public void testGetMolecule() throws IOException {
		// add one molecule to store
		URL url = this.getClass().getClassLoader().getResource(TEST_MOL);
		byte[] bytes = IOUtils.toByteArray(url);
		Molecule molecule = MolImporter.importMol(bytes);
		MoleculeInfo info = datastore.saveMolecule(bytes, molecule, TEST_MOL);
		// check if datastore returns same molecule as previously saved
		Assert.assertEquals(molecule.getFormula(),
				datastore.getMolecule(info.getId()).getFormula());

		cleanup(folder.getRoot().getAbsolutePath() + File.separator
				+ info.getFilename());
	}

	/**
	 * Remove .mol and .molinfo files from store.
	 * 
	 * @param path
	 */
	private void cleanup(String path) {

		new File(path).delete();
		new File(path + SimpleMoleculeDatastore.INFO_EXTENSION).delete();

	}

}
