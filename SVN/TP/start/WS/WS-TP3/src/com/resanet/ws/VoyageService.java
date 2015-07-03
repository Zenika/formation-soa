package com.resanet.ws;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

public class VoyageService {

	private Map<Integer, Voyage> voyages = new HashMap<Integer, Voyage>();
	{
		{
			Voyage voyage = new Voyage();
			voyage.setId(1);
			voyage.setLibelle("Paris-Rennes");
			voyages.put(voyage.getId(), voyage);
		}

		{
			Voyage voyage = new Voyage();
			voyage.setId(2);
			voyage.setLibelle("Paris-Lyon");
			voyages.put(voyage.getId(), voyage);
		}
	}

	public Voyage getVoyage(Integer id) {
		return voyages.get(id);
	}

	public void updateVoyage(Integer id, Voyage voyage) {
		if (voyages.get(id) == null) {
			throw new NotFoundException(
					"Impossible de mettre à jour le voyage " + id);
		} else {
			voyages.put(id, voyage);
		}
	}

	public Response addVoyage(Voyage voyage) {
		voyages.put(voyage.getId(), voyage);
		return Response.created(URI.create("/" + voyage.getId())).build();
	}

	public void deleteVoyage(Integer id) {
		if (voyages.get(id) == null) {
			throw new NotFoundException("Impossible de supprimer le voyage "
					+ id);
		} else {
			voyages.remove(id);
		}
	}

	public Collection<Voyage> listVoyages() {
		return voyages.values();
	}

}
