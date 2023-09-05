package ru.practicum.explorewithme;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ru.practicum.explorewithme.constant.Constants.DATE_TIME_FORMATTER;

@Service
@RequiredArgsConstructor
public class StatisticsClient {

    @Value("${stats-server.url}")
    private String serverUrl;


    private final RestTemplate restTemplate;

    @Autowired
    public StatisticsClient(@Value("${statistics.server.address}") String serverUrl, RestTemplateBuilder builder) {
        restTemplate = builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl)).requestFactory(HttpComponentsClientHttpRequestFactory::new).build();
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.valueOf(DATE_TIME_FORMATTER));

    public void createHit(RequestHitDto endpointHitRequestDto) {
        restTemplate.postForLocation(serverUrl.concat("/hit"), endpointHitRequestDto);
    }

    public List<ResponseHitDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        Map<String, Object> parameters = new HashMap<>(Map.of("start", start.format(formatter), "end", end.format(formatter), "unique", unique));

        if (uris != null && !uris.isEmpty()) {
            parameters.put("uris", String.join(",", uris));
        }

        ResponseHitDto[] response = restTemplate.getForObject(serverUrl.concat("/stats?start={start}&end={end}&uris={uris}&unique={unique}"), ResponseHitDto[].class, parameters);

        return Objects.isNull(response) ? List.of() : List.of(response);
    }

}
