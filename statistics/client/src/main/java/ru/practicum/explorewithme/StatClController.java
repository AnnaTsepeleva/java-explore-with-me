package ru.practicum.explorewithme;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import  ru.practicum.explorewithme.log.ToLog;
import ru.practicum.explorewithme.validator.StartBeforeEndDateConstraint;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.explorewithme.constant.Constants.DATE_TIME_FORMAT;

@Controller
@RequiredArgsConstructor
@Validated
@ToLog
public class StatClController {
    private final StatClientService client;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    @Valid
    public ResponseEntity<Object> createHit(@RequestBody RequestHitDto hitDto) {
        return client.postHit(hitDto);
    }

    @GetMapping("/stats")
    @StartBeforeEndDateConstraint
    public ResponseEntity<Object> getStats(@RequestParam(name = "start") @DateTimeFormat(fallbackPatterns = DATE_TIME_FORMAT) LocalDateTime start,
                                           @RequestParam(name = "end") @DateTimeFormat(fallbackPatterns = DATE_TIME_FORMAT) LocalDateTime end,
                                           @RequestParam(name = "uris", required = false) List<String> uris,
                                           @RequestParam(name = "unique", defaultValue = "false") boolean unique) {
        return client.getStats(start, end, uris, unique);
    }
}
