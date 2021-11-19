package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.db.DB;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> pets = new ArrayList<Pet>();
		Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(3);
		pet1.setPetName("Boola");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Sudda");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(3);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		DB.getPetTable().add(pet1);
		DB.getPetTable().add(pet2);
		DB.getPetTable().add(pet3);
		return Response.ok(DB.getPetTable()).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(3);
		pet.setPetName("Boola");
		pet.setPetType("Dog");

		return Response.ok(pet).build();
		
	}
//	@APIResponses(value = {
//			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
//			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@POST
	@Path("addPet")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addPet(@RequestBody Pet pet) {

		//return Response.ok(pet).build();
		DB.savePet(pet);


	}

	//update
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePet(@RequestBody Pet pet) {
		Pet updatedPet = null;
		for (Pet pet1 : DB.getPetTable()) {
			if (pet1.getPetId() == pet.getPetId()){
//				updatedPet= DB.updatePet(pet1, pet);
				pet1.setPetName(pet.getPetName());
				pet1.setPetAge(pet.getPetAge());
				pet1.setPetType(pet.getPetType());
				updatedPet=pet1;
			}
		}
		return Response.ok(updatedPet).build();
	}

//delete
@PUT
@Path("deletePet")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void deletePet(@RequestBody Pet pet) {
	Pet updatedPet = null;
	for (Pet pet1 : DB.getPetTable()) {
		if (pet1.getPetId() == pet.getPetId()){
//				updatedPet= DB.updatePet(pet1, pet);
//			pet1.setPetName(pet.getPetName());
//			pet1.setPetAge(pet.getPetAge());
//			pet1.setPetType(pet.getPetType());
//			updatedPet=pet1;
			DB.deletePet(pet1);
		}
	}
//	return Response.ok(updatedPet).build();
}
}


