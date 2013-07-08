package cz.sefware.jchem.model;

import cz.sefware.jchem.enums.FileFormat;

public class MoleculeInfo {

	private String filename;
	private String name;
	private Long id;
	private FileFormat format;

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

	public FileFormat getFormat() {
		return format;
	}

	public void setFormat(FileFormat format) {
		this.format = format;
	}

}
