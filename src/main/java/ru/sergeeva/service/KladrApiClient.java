package ru.sergeeva.service;

import ru.sergeeva.domain.KladrObject;

import java.util.List;

public class KladrApiClient {

    public List<KladrObject> getKladrDistricts(String query, Integer limit) {
        return getKladrDistricts(query, limit, null);
    }

    public List<KladrObject> getKladrDistricts(String query, Integer limit, Boolean withParent) {
        String params =
                new KladrApiUrlBuilder()
                        .setContentType(KladrApiUrlBuilder.CONTENT_TYPE_DISTRICT)
                .setWithParent(withParent).setLimit(limit).setQuery(query).toString();

        List<KladrObject> districtList = getKladrObjectList(params);
        return districtList;
    }

    private List<KladrObject> getKladrObjectList(String params) {
        return null;
    }
}
