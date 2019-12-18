package cwansart.reports.infrastructure;

import javax.json.bind.adapter.JsonbAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateAdapter implements JsonbAdapter<LocalDate, String> {

    @Override
    public String adaptToJson(final LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public LocalDate adaptFromJson(final String date) {
        LocalDate parsedDate;

        System.out.println("converting date=" + date);

        try {
            if (date.contains("T")) {
                parsedDate = LocalDate.parse(date.split("T")[0], DateTimeFormatter.ISO_DATE);
            } else {
                parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            }
        } catch (final DateTimeParseException e) {
            // You could throw another exception here for the validation.
            parsedDate = LocalDate.now();
        }

        return parsedDate;
    }
}
