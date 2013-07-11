package cz.sefware.jchem.model;

/**
 * Simple model for holding metadata about Molecule objects stored on server.
 * 
 * @author jg
 * 
 */
public class MoleculeInfo {

	private String filename;
	private String name;
	private Long id;
	private String format;
	private String formula;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

}
