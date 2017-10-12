package ru.sergeeva.service;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergeeva.domain.*;
import ru.sergeeva.web.ApiExchangeClient;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class ApiService {

    @Autowired
    private ApiExchangeClient apiExchangeClient;

    public List<KladrObject> findAll() {
        return apiExchangeClient.getStreetsByCityId("К", "7400000900000", 99).stream()
                .map(this::toCity)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private KladrObject toCity(@NonNull KladrDto input) {
        return new KladrObject(Long.valueOf(input.getId()),
                input.getName(),
                Integer.valueOf(input.getZip()),
                input.getType(), input.getParents());
    }

}
