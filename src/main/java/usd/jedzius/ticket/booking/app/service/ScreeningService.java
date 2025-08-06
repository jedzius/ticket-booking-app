package usd.jedzius.ticket.booking.app.service;

import org.springframework.stereotype.Service;
import usd.jedzius.ticket.booking.app.model.Screening;
import usd.jedzius.ticket.booking.app.model.dto.ScreeningDTO;
import usd.jedzius.ticket.booking.app.repository.ScreeningRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<ScreeningDTO> getAllScreenings(LocalDateTime start, LocalDateTime end) {
        final List<Screening> screenings = screeningRepository.findAll();
        screenings.sort(Comparator.comparing((Screening s) -> s.getPendingMovie().getId()).thenComparing(Screening::getStartTime));

        final Map<Integer, List<LocalDateTime>> screeningsMap = screenings.stream()
                .collect(Collectors.groupingBy(
                        screening -> screening.getPendingMovie().getId(),
                        TreeMap::new,
                        Collectors.mapping(Screening::getStartTime, Collectors.toList())
                ));


        return screeningsMap.entrySet().stream().map(entry -> new ScreeningDTO(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }
}
