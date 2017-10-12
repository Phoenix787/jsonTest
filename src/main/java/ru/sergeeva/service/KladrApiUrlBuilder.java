package ru.sergeeva.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@NoArgsConstructor
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
    private Boolean withParent;

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

    public KladrApiUrlBuilder setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public KladrApiUrlBuilder setQuery(String query) {
        this.query = query;
        return this;
    }

    public KladrApiUrlBuilder setWithParent(boolean withParent) {
        this.withParent = withParent;
        return this;
    }

    public KladrApiUrlBuilder setCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }
    public KladrApiUrlBuilder setregionId(String regionId) {
        this.regionId = regionId;
        return this;
    }
    public KladrApiUrlBuilder setDistrictId(String cityId) {
        this.districtId = districtId;
        return this;
    }

    public KladrApiUrlBuilder setBuildingId(String buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public String encode(String string) throws UnsupportedEncodingException {
        return encode(string, "UTF-8");
    }

    public String encode(String string, String encoding) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, encoding);
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(Boolean encoding) {
        if (this.contentType == null) {
            throw new NullPointerException("contentType is required");
        }
        if (this.contentType.equalsIgnoreCase(CONTENT_TYPE_STREET) && cityId == null) {
            throw new NullPointerException("cityId is required when contentType is street");
        }
        StringBuilder builder = new StringBuilder();
        builder.append("&contentType=").append(this.contentType);

        if (regionId != null) {
            builder.append("&regionId=").append(this.regionId);
        }
        if (districtId != null) {
            builder.append("&district=").append(this.districtId);
        }
        if (cityId != null) {
            builder.append("&cityId=").append(this.cityId);
        }
        if (buildingId != null) {
            builder.append("&builderId=").append(this.buildingId);
        }
        if (this.withParent != null){
            builder.append("&withParent=1");
        }
        if (this.limit != null) {
            builder.append("&limit=").append(this.limit);
        }
        if (query != null) {
            if (encoding) {
                try {
                    builder.append("&query=").append(encode(this.query));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    builder.append("&query=").append(this.query);
                }
            } else
                builder.append("&query=").append(this.query);
        }
        return builder.toString();
    }
}
