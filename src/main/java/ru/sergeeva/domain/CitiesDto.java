package ru.sergeeva.domain;
//объект, который содержит информацию запрос с сайта kladr-api.ru
// что содержит данный объект. судя по данным на сайте это массив из двух элеметнов:
//searchContext and result, которые сами являются массивами
//searchContext содержит данные о запросе: searchContext: {contentType: "city", query: "Магн", withParent: "1", limit: 10}
//можно сформировать объект с этими полями
//result - это массив из однотипных объектов с полями:
//        contentType:"city"
//        id:"7400000900000"
//        name:"Магнитогорск"
//        okato:"75438000000"
//        parents: [{id: "7400000000000", name: "Челябинская", zip: 454000, type: "Область", typeShort: "обл",…}]
//        type:"Город"
//        typeShort:"г"
//        zip:455000


import lombok.Data;

import java.util.List;
@Data
public class CitiesDto {
    private List<CityDto> result;
    private SearchContext searchContext;

}
