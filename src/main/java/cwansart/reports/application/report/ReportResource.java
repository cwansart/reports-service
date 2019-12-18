package cwansart.reports.application.report;

import cwansart.reports.domain.report.Report;
import cwansart.reports.domain.report.ReportRepository;
import cwansart.reports.domain.report.ReportService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Path("reports")
@Consumes(MediaType.APPLICATION_JSON)
public class ReportResource {

    @Inject
    private ReportRepository repository;

    @Inject
    private ReportService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReports() {
        return Response.ok(repository.getAllReports().stream()
            .map(ReportDto::new)
            .collect(Collectors.toList()))
            .build();
    }

    @GET
    @Path("{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReport(
        @NotNull @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") @PathParam("date") String date
    ) {
        Report report = repository.getReport(Date.valueOf(date));

        if (report == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(new ReportDto(report)).build();
        }
    }

    @POST
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response createReport(
        @NotNull @Valid CreateReportDto report
    ) throws URISyntaxException {

        LocalDate creationDate = service.createReport(report);

        URI uri = new URI(String.format("/reports/%s", creationDate.format(DateTimeFormatter.ISO_DATE)));

        return Response.created(uri).build();
    }

    @PUT
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReport(
        @NotNull @Valid CreateReportDto report
    ) {
        System.out.println("received dto=" + report);

        boolean updated = service.updateReport(report);

        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }
}
