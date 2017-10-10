package ru.sergeeva.service;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergeeva.domain.*;
import ru.sergeeva.web.ApiExchangeClient;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class ApiService {

    @Autowired
    private ApiExchangeClient apiExchangeClient;

    public List<City> findAll() {
        return apiExchangeClient.getCities().stream()
                .map(this::toCity)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    public SearchContext findContext(){
        return apiExchangeClient.getContext();
    }

    private City toCity(@NonNull CityDto input) {
        return new City(Long.valueOf(input.getId()),
                input.getName(),
                Integer.valueOf(input.getZip()),
                input.getType());
    }

}
