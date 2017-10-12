package ru.sergeeva.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
//объект в java
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class KladrObject {
    private Long id;
    private String name;
    private int zip;
    private String type;
    private List<KladrObject> parents;

    public KladrObject(Long id, String name, int zip, String type, List<KladrDto> parents) {
        super();
        if (id != null){
            this.id = id;
        } else{
            throw new NullPointerException("it can't be null");
        }
        this.name = name;
        this.zip = zip;
        this.type = type;
      //  this.typeShort = typeShort;
//        this.okato = okato;

        this.parents = toKladrObjectParents(parents);

    }

    private List<KladrObject> toKladrObjectParents(List<KladrDto> parents) {
        if (parents != null) {
            List<KladrObject> list = new ArrayList<>();
            for (KladrDto parent : parents) {
                KladrObject city = new KladrObject(Long.valueOf(parent.getId()),
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
