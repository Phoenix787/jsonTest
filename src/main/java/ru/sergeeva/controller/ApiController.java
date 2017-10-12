package ru.sergeeva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sergeeva.domain.KladrObject;
import ru.sergeeva.domain.SearchContext;
import ru.sergeeva.service.ApiService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

//        @RequestMapping("/json")
//        private String getCity() {
//        List<KladrObject> all = apiService.findAll();
//            StringBuilder buffer = new StringBuilder();
//            for (KladrObject city : all) {
//                buffer.append(" KladrObject: ").append(city.getId())
//                        .append(" ").append(city.getName())
//                        .append(" ").append(city.getZip())
//                        .append(" ").append(city.getType());
//            }
//        return buffer.toString();
//    }

    @RequestMapping("/json")
    private List<KladrObject> getCity() {
        return apiService.findAll(); //можно попробовать использовать как datasource для компонента.
    }

}
