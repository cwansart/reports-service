package cwansart.reports.domain.report;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class ReportRepository {

    @PersistenceContext(unitName = "report-unit")
    private EntityManager entityManager;

    public Report getReport(Date creationDate) {
        System.out.println(String.format("get selection by creation date %s",
            creationDate.toLocalDate().format(DateTimeFormatter.ISO_DATE)));

        TypedQuery<Report> query = entityManager.createQuery(
            "select r from Report r where r.creationDate = :creationDate", Report.class);
        query.setParameter("creationDate", creationDate);

        List<Report> reports = query.getResultList();
        if (!reports.isEmpty()) {
            System.out.println(String.format("found report with creation date %s",
                creationDate.toLocalDate().format(DateTimeFormatter.ISO_DATE)));
            return reports.get(0);
        } else {
            System.out.println(String.format("did not find report with creation date %s",
                creationDate.toLocalDate().format(DateTimeFormatter.ISO_DATE)));
            return null;
        }
    }

    public List<Report> getAllReports() {
        System.out.println("get all reports");

        List<Report> reports = entityManager.createQuery("select r from Report r", Report.class).getResultList();

        System.out.println(String.format("found %d reports", reports.size()));

        return reports;
    }

    Date createReport(Report report) {
        System.out.println("create report");

        entityManager.persist(report);

        System.out.println(String.format("created report with creation date %s",
            report.getCreationDate().toLocalDate().format(DateTimeFormatter.ISO_DATE)));

        return report.getCreationDate();
    }

    void updateReport(Report report) {
        System.out.println(String.format("update report with creation date %s",
            report.getCreationDate().toLocalDate().format(DateTimeFormatter.ISO_DATE)));

        entityManager.merge(report);

        System.out.println(String.format("updated report with %s",
            report.getCreationDate().toLocalDate().format(DateTimeFormatter.ISO_DATE)));
    }
}
