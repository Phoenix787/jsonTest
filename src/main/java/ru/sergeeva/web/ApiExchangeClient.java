package ru.sergeeva.web;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sergeeva.domain.KladrObjectsDto;
import ru.sergeeva.domain.KladrDto;
import ru.sergeeva.domain.SearchContext;
import ru.sergeeva.service.KladrApiUrlBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//класс-клиент, который будет общаться с сервисом kladr-api
//данный клиент ничего не знает о нашей бизнес-логике, он должен вернуть что-то специфическое
//результат работы - результат взаимодействия с api сервера
//теоритически можно создать объект, который будет содержать всю информацию с api



@Component
public class ApiExchangeClient {
    Logger logger = Logger.getLogger(ApiExchangeClient.class.getName());

    private RestTemplate restTemplate = new RestTemplate();
    //private String baseurl = "http://kladr-api.ru/api.php";

    public List<KladrDto> getDistricts(String query, int limit) {
        return getDistricts(query, limit, false);
    }
//методы работы с районами
    public List<KladrDto> getDistricts(String query, int limit, Boolean withParent) {
        String params = new KladrApiUrlBuilder().setContentType(KladrApiUrlBuilder.CONTENT_TYPE_DISTRICT)
                .setWithParent(withParent)
                .setQuery(query)
                .setLimit(limit)
                .toString();
        return getKladrObject(params);
    }

    /**
     * Returns list of names of district. Dublicates are omitted
     * @param query - search query
     * @param limit - max number of returned objects
     * @return list of names as String
     */
    public List<String> getDistrictNames(String query, Integer limit) {
        return extractKladrObjectListName(getDistricts(query, limit));
    }

    //методы работы с городами
    /** method returns list of cities without parent
     * @param query - search query
     * @param limit - max number of returned object
     * @return list of cities as KladrDto
     */
     public List<KladrDto> getCities(String query, Integer limit) {
        return getCities(query, limit, false);
    }

    //методы работы с городами
    /** method returns list of cities without parent
     * @param query - search query
     * @param limit - max number of returned object
     * @param withParent - return parents (region, district)
     * @return list of cities as KladrDto
     */
    public List<KladrDto> getCities(String query, Integer limit, Boolean withParent) {
        String params = new KladrApiUrlBuilder().setContentType(KladrApiUrlBuilder.CONTENT_TYPE_CITY)
                .setQuery(query).setWithParent(withParent).setLimit(limit).toString();
        return getKladrObject(params);
    }

    /**
     * Returns list of names of cities. Dublicates are omitted
     * @param query - search query
     * @param limit - max number of returned objects
     * @return list of names as String
     */
    public List<String> getCityNames(String query, int limit) {
        return extractKladrObjectListName(getCities(query, limit));
    }

    /**
     * Returns list of streets, specified by KLADR city code
     * @param query - search query
     * @param cityId - KLADR city code
     * @param limit - max number of returned objects
     * @return list of streets as KladDto
     */
    public List<KladrDto> getStreetsByCityId(String query, String cityId, int limit) {
        String params = new KladrApiUrlBuilder().setContentType(KladrApiUrlBuilder.CONTENT_TYPE_STREET)
                .setCityId(cityId).setQuery(query).setLimit(limit).toString();

        return getKladrObject(params);
    }

    public List<KladrDto> getStreetByCityName(String query, String cityName, int limit){
        List<KladrDto> streets = null;
        List<KladrDto> cities = getCities(cityName, 1);
        if (cities.size() > 0) {
            KladrDto city = cities.get(0);
            streets = getStreetsByCityId(query, city.getName(), limit);
        }
        return streets;
    }

    public List<String> getStreetNamesByCityId(String query, String cityId, int limit) {
        return extractKladrObjectListName(getStreetsByCityId(query, cityId, limit));
    }

    public List<String> getStreetNamesByCityName(String query, String cityName, int limit) {
        return extractKladrObjectListName(getStreetByCityName(query, cityName, limit));
    }


    public List<KladrDto> getKladrObject(String params) {

        try {
            KladrObjectsDto response = restTemplate.getForObject(new URI(queryKladrApi(params)), KladrObjectsDto.class);

            return response.getResult();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> extractKladrObjectListName(List<KladrDto> kladrDtoList) {
        List<String> nameList = new ArrayList<>();
        if (!kladrDtoList.isEmpty()) {
            String prevName = null;
            for (KladrDto kladrDto : kladrDtoList) {
                String curName = kladrDto.getName();
                if (prevName == null || !prevName.equals(curName)){
                    nameList.add(curName);
                    prevName = curName;
                }
            }
        }
        return nameList;
    }




    @NotNull
    private String queryKladrApi(String params) {
        String urlString = KladrApiUrlBuilder.getBaseUrl();
        if (params.startsWith("&")) {
            params = params.substring(params.indexOf("&") + 1);
        }
        return urlString + "?" + params;
    }
}
