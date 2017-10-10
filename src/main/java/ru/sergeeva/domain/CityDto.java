package ru.sergeeva.domain;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityDto {
    private String contentType;
    private String id;
    private String name;
    private String okato;
    private List<CityDto> parents;
    private String type;
    private String typeShort;
    private String zip;


//    public CityDto(String id, String name, String zip, String type, String typeShort, String okato, List<CityDto> parents) {
//        super();
//        if (id != null){
//            this.id = id;
//        } else{
//            throw new NullPointerException("it can't be null");
//        }
//        if (id != null){
//            this.name = name;
//        } else{
//            throw new NullPointerException("it can't be null");
//        }
//        this.zip = zip;
//        this.type = type;
//        this.typeShort = typeShort;
//        this.okato = okato;
//        this.parents = parents;
//    }
//
//    public CityDto(JSONObject kladrObject){
//        super();
//        try {
//           this.id = kladrObject.getString("id");
//        } catch (JSONException e) {
//            throw new NullPointerException("it can't be null");
//        }
//
//        try {
//            this.name = kladrObject.getString("name");
//        } catch (JSONException e) {
//            throw new NullPointerException("it can't be null");
//        }
//        this.zip = kladrObject.optString("zip");
//        this.type = kladrObject.optString("type");
//        this.typeShort = kladrObject.optString("typeShort");
//        this.okato = kladrObject.optString("okato");
//
//        List<CityDto> parents = new ArrayList<>();
//        JSONArray parentArray = kladrObject.optJSONArray("parents");
//        if (parentArray != null) {
//            for (int i = 0; i < parentArray.length(); i++) {
//                try {
//                    JSONObject jsonObject = parentArray.getJSONObject(i);
//                    CityDto cityDto = new CityDto(jsonObject);
//                    parents.add(cityDto);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            this.parents = parents;
//        }
//
//    }
}
