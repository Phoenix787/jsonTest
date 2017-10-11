package ru.sergeeva.domain;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class KladrObject {
    private String id;
    private String name;
    private String zip;
    private String type;
    private String typeShort;
    private String okato;
    private List<KladrObject> parents;

    public KladrObject() {

    }

    public KladrObject(String id, String name, String zip, String type, String typeShort, String okato,
                       List<KladrObject> parents) {
        super();

        if (id == null || id.length() == 0) {
            throw new NullPointerException("id can't be null");
        }
        if (name == null || name.length() == 0) {
            throw new NullPointerException("name can't be null");
        }

        this.id = id;
        this.name = name;
        this.zip = zip;
        this.type = type;
        this.typeShort = typeShort;
        this.okato = okato;
        this.parents = parents;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.typeShort.equals("проезд") || this.typeShort.equals("км")) {
            // exclusion
            builder.append(name).append(" ").append(typeShort);
        } else {
            builder.append(typeShort).append(". ").append(name);
        }

        // or easier way
        // builder.append(this.name).append(" ").append(typeShort);

        if (parents != null && !parents.isEmpty()) {
            int parentsSize = parents.size();
            builder.append(" (");
            for (int i = 0; i < parentsSize; i++) {
                KladrObject parent = parents.get(parentsSize - i - 1);
                builder.append(parent.getName()).append(" ").append(parent.getTypeShort());
                builder.append(".");
                if (i != parentsSize - 1) {
                    builder.append(", ");
                }
            }
            builder.append(")");
        }
        return builder.toString();
    }

    public List<KladrObject> getParents() {
        return parents != null ? parents : Collections.<KladrObject>emptyList();
    }
}
