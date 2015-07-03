package com.resanet.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Voyage {

	private Integer id;

	private String libelle;

	// //////////////////////////////////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		}
		return ((id != null) && (id.equals(((Voyage) obj).id)));
	}

	@Override
	public int hashCode() {
		return libelle.hashCode();
	}

}
