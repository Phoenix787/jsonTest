package ru.sergeeva.service;

import lombok.Data;

@Data
public class KladrApiUrlBuilder {
    public static final String KLADR_API_BASE_URL = "http://kladr-api.ru/api.php";
    public static final String CONTENT_TYPE_REGION = "region";
    public static final String CONTENT_TYPE_DISTRICT = "district";
    public static final String CONTENT_TYPE_CITY = "city";
    public static final String CONTENT_TYPE_STREET = "street";
    public static final String CONTENT_TYPE_BUILDING = "building";

    private String contentType;
    private String query;
    private String regionId;
    private String districtId;
    private String cityId;
    private String buildingId;
    private Integer limit;
    private boolean withParent;

    public KladrApiUrlBuilder(String contentType, String query, Integer limit) {
        super();
        setContentType(contentType);
        this.query = query;
        this.limit = limit;
    }

    public static String getBaseUrl() {
        return KLADR_API_BASE_URL;
    }

    public KladrApiUrlBuilder setContentType(String contentType){
        if (!(contentType.equalsIgnoreCase(CONTENT_TYPE_DISTRICT)
                || contentType.equalsIgnoreCase(CONTENT_TYPE_CITY)
                || contentType.equalsIgnoreCase(CONTENT_TYPE_STREET)
                || contentType.equalsIgnoreCase(CONTENT_TYPE_BUILDING))){
            throw new NullPointerException("contentType must be one of the following: " +
                    CONTENT_TYPE_REGION + CONTENT_TYPE_DISTRICT + ", " + CONTENT_TYPE_CITY +
                    ", " + CONTENT_TYPE_STREET + ", " + CONTENT_TYPE_BUILDING);

        }
        this.contentType = contentType;
        return this;
    }
}
