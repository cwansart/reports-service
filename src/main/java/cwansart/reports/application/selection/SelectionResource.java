package cwansart.reports.application.selection;

import cwansart.reports.domain.selection.Selection;
import cwansart.reports.domain.selection.SelectionBuilder;
import cwansart.reports.domain.selection.SelectionRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@Path("selections")
@Consumes(MediaType.APPLICATION_JSON)
public class SelectionResource {

    @Inject
    private SelectionRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSelections() {
        System.out.println("get all selections");

        return Response.ok(repository.getAllSelections().stream()
            .map(SelectionDto::new)
            .collect(Collectors.toList()))
            .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSelection(
        @PathParam("id") Long id
    ) {
        System.out.println(String.format("get selection with id %s", id));

        Selection selection = repository.getSelection(id);

        if (selection == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(selection).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response createSelection(
        @NotNull @Valid CreateSelectionDto selection
    ) throws URISyntaxException {

        System.out.println("create selection");

        Long id = repository.createSelection(SelectionBuilder.newBuilder()
            .withTitle(selection.getTitle())
            .withDescription(selection.getDescription())
            .build());

        return Response.created(new URI(String.format("/selections/%d", id))).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteSelection(
        @PathParam("id") Long id
    ) {
        System.out.println("delete selection");

        Selection selection = repository.getSelection(id);

        if (selection == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        repository.deleteSelection(selection);

        return Response.noContent().build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateSelection(
        @PathParam("id") Long id,
        @NotNull @Valid CreateSelectionDto dto
    ) {
        System.out.println("update selection");

        Selection selection = repository.getSelection(id);

        if (selection == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        selection.setTitle(dto.getTitle());
        selection.setDescription(dto.getDescription());

        repository.updateSelection(selection);

        return Response.noContent().build();
    }
}
