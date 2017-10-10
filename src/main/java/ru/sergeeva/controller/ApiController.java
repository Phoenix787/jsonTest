package ru.sergeeva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergeeva.domain.City;
import ru.sergeeva.domain.SearchContext;
import ru.sergeeva.service.ApiService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @RequestMapping("/json")
    private List<City> getCity() {
        return apiService.findAll();
    }
    @RequestMapping("/context")
    private SearchContext getContext(){
        return apiService.findContext();
    }
}
