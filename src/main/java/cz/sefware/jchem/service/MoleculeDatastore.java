package cz.sefware.jchem.service;

import java.util.List;

import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;

public interface MoleculeDatastore {

	/**
	 * Lists all molecule files in datastore.
	 * 
	 * @return
	 */
	public abstract List<MoleculeInfo> getMoleculeInfos();

	/**
	 * Saves Molecule to datastore.
	 * 
	 * @param bytes
	 *            original uploaded data
	 * @param molecule
	 *            parsed molecule object
	 * @return MoleculeInfo describing given molecule
	 */
	public abstract MoleculeInfo saveMolecule(byte[] bytes, Molecule molecule,
			String originalFilename);

	/**
	 * Fetches molecule from datastore by id.
	 * 
	 * @param id
	 * @return
	 */
	public abstract Molecule getMolecule(Long id);

}