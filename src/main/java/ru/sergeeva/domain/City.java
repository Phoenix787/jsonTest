package ru.sergeeva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Long id;
    private String name;
    private int zip;
    private String type;
    private List<City> parents;

    public City(Long id, String name, int zip, String type, List<CityDto> parents) {
        this.id = id;
        this.name = name;
        this.zip = zip;
        this.type = type;
        this.parents = toCityParents(parents);


    }

    private List<City> toCityParents(List<CityDto> parents) {
        if (parents != null) {
            List<City> list = new ArrayList<>();
            for (CityDto parent : parents) {
                City city = new City(Long.valueOf(parent.getId()),
                        parent.getName(),
                        Integer.valueOf(parent.getZip()),
                        parent.getType(), null);
                list.add(city);

            }
            return list;
        }
        else return null;

    }
}
