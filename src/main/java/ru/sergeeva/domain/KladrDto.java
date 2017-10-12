package ru.sergeeva.domain;

import lombok.Data;

import java.util.List;
 //объект заданного типа
@Data
public class KladrDto {
    private String contentType;
    private String id;
    private String name;
    private String okato;
    private List<KladrDto> parents;
    private String type;
    private String typeShort;
    private String zip;


//    public KladrDto(String id, String name, String zip, String type, String typeShort, String okato, List<KladrDto> parents) {
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
//    public KladrDto(JSONObject kladrObject){
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
//        List<KladrDto> parents = new ArrayList<>();
//        JSONArray parentArray = kladrObject.optJSONArray("parents");
//        if (parentArray != null) {
//            for (int i = 0; i < parentArray.length(); i++) {
//                try {
//                    JSONObject jsonObject = parentArray.getJSONObject(i);
//                    KladrDto cityDto = new KladrDto(jsonObject);
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
