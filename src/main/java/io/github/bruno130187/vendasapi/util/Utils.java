package io.github.bruno130187.vendasapi.util;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {

    public static Timestamp convertStringToDate(String dataString, boolean atEndOfDay) {
        if (!StringUtils.hasText(dataString)) {
            return null;
        }
        var data = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDateTime dataHora;
        if (atEndOfDay) {
            dataHora = data.atTime(LocalTime.of(23,59, 59));
        } else {
            dataHora = data.atStartOfDay();
        }
        Instant instant = dataHora.atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
        return Timestamp.from(instant);
    }

}
