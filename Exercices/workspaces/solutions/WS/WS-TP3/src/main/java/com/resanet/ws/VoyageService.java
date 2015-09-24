package com.resanet.ws;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("voyages")
@Consumes("application/xml")
@Produces("application/xml")
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

	@GET
	@Path("{id}")
	public Voyage getVoyage(@PathParam("id") Integer id) {
		return voyages.get(id);
	}

	@PUT
	@Path("{id}")
	public void updateVoyage(@PathParam("id") Integer id, Voyage voyage) {
		if (voyages.get(id) == null) {
			throw new NotFoundException(
					"Impossible de mettre à jour le voyage " + id);
		} else {
			voyages.put(id, voyage);
		}
	}

	@POST
	public Response addVoyage(Voyage voyage) {
		voyages.put(voyage.getId(), voyage);
		return Response.created(URI.create("/" + voyage.getId())).build();
	}

	@DELETE
	@Path("{id}")
	public void deleteVoyage(@PathParam("id") Integer id) {
		if (voyages.get(id) == null) {
			throw new NotFoundException("Impossible de supprimer le voyage "
					+ id);
		} else {
			voyages.remove(id);
		}
	}

	@GET
	public Collection<Voyage> listVoyages(@QueryParam("libelle") String libelle) {
		if (libelle == null) {
			return voyages.values();
		} else {
			List<Voyage> liste = new ArrayList<Voyage>();
			for (Voyage voyage : voyages.values()) {
				if (voyage.getLibelle().equals(libelle)) {
					liste.add(voyage);
				}
			}
			return liste;
		}
	}

}
