package ru.sitronics.tn.document.util;
/*

import ru.sitronics.tn.document.dto.DocumentDto;
import ru.sitronics.tn.document.model.DocumentOld;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class DocumentUtil {

    public static List<DocumentDto> getTos(Collection<DocumentOld> documents, long sumAmount) {
        return filterByPredicate(documents, sumAmount, document -> true);
    }

    public static List<DocumentDto> getFilteredTos(Collection<DocumentOld> documents, long sumAmount, LocalTime startTime, LocalTime endTime) {
        return filterByPredicate(documents, sumAmount, document -> Util.isBetweenHalfOpen(
                document.getCreated().toLocalTime(), startTime, endTime));
    }

    public static List<DocumentDto> filterByPredicate(Collection<DocumentOld> documents, long sumAmount, Predicate<DocumentOld> filter) {
        Map<LocalDate, Long> sumByDate = documents.stream()
                .collect(
                        Collectors.groupingBy(document -> document.getCreated().toLocalDate(), Collectors.summingLong(DocumentOld::getAmount))
//                      Collectors.toMap(Document::getDate, Document::getAmount, Long::sum)
                );

        return documents.stream()
                .filter(filter)
                .map(document -> createTo(document, sumByDate.get(document.getCreated().toLocalDate()) > sumAmount))
                .toList();
    }

    public static DocumentDto createTo(DocumentOld document, boolean excess) {
        return new DocumentDto(document.getId(), document.getName(), document.getNumber(), document.getContent(), document.getStatus(),
                document.getCustomer(), document.getSupplier(), document.getAmount(),
                document.getCreated(), document.getModified());
    }
}
*/
