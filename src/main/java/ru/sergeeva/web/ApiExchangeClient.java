package ru.sergeeva.web;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sergeeva.domain.CitiesDto;
import ru.sergeeva.domain.CityDto;
import ru.sergeeva.domain.KladrObject;
import ru.sergeeva.domain.SearchContext;
import ru.sergeeva.service.KladrApiUrlBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

//класс-клиент, который будет общаться с сервисом kladr-api
//данный клиент ничего не знает о нашей бизнес-логике, он должен вернуть что-то специфическое
//результат работы - результат взаимодействия с api сервера
//теоритически можно создать объект, который будет содержать всю информацию с api
@Component
public class ApiExchangeClient {

    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://kladr-api.ru/api.php?query=%D0%9C%D0%B0%D0%B3%D0%BD&contentType=city&withParent=1&limit=5";

    public List<CityDto> getCities() {
//формируем здесь строку для запроса города
        try {
            CitiesDto response = restTemplate.getForObject(new URI(url), CitiesDto.class);
            return response.getResult();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public SearchContext getContext(){

        try {
            CitiesDto response = restTemplate.getForObject(new URI(url), CitiesDto.class);
            return response.getSearchContext();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public List<CityDto> getDistricts(String query, Integer limit) {
        return getDistricts(query, limit, null);
    }

    public List<CityDto> getDistricts(String query, Integer limit, Boolean withParent) {
//        String params =
//                new KladrApiUrlBuilder()
//                        .setContentType(KladrApiUrlBuilder.CONTENT_TYPE_DISTRICT)
//                        .setWithParent(withParent).setLimit(limit).setQuery(query).toString();
        String url = "http://kladr-api.ru/api.php?query=" + query
                + "contentType=" + KladrApiUrlBuilder.CONTENT_TYPE_DISTRICT
                + "&withParent=" + (withParent == null ? "0" : "1") + "&limit=" + limit;

        try {
            CitiesDto response = restTemplate.getForObject(new URI(url), CitiesDto.class);
            return response.getResult();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public List<CityDto> get(String query, Integer limit, Boolean withParent) {
//        String params =
//                new KladrApiUrlBuilder()
//                        .setContentType(KladrApiUrlBuilder.CONTENT_TYPE_DISTRICT)
//                        .setWithParent(withParent).setLimit(limit).setQuery(query).toString();
        String url = "http://kladr-api.ru/api.php?query=" + query
                + "contentType=" + KladrApiUrlBuilder.CONTENT_TYPE_DISTRICT
                + "&withParent=" + (withParent == null ? "0" : "1") + "&limit=" + limit;

        try {
            CitiesDto response = restTemplate.getForObject(new URI(url), CitiesDto.class);
            return response.getResult();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

//    private List<KladrObject> getKladrObjectList(String params) {
//        String url = "http://kladr-api.ru/api.php?query=%D0%9C%D0%B0%D0%B3%D0%BD&contentType=city&withParent=1&limit=5";
//        return null;
//    }
}
