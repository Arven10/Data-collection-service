package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dorozhan on 30.04.2015.
 */

@Entity
@Table(name = "field")
@SequenceGenerator(name = "field_id_generator", sequenceName = "field_field_id_sequence", allocationSize = 1)
public class Field {
    private int id;
    private String label;
    //private TypeOfField type;
    private String type;
    private boolean required;
    private boolean active;
    private List<Option> options;

    public Field() {
    }

    public Field(String label, String type, boolean required, boolean active) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.active = active;
    }

    @Id
    @GeneratedValue
    @Column(name = "field_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

//    @Column(name = "type")
//    @Enumerated(EnumType.STRING)
//    public TypeOfField getType() {
//        return type;
//    }
//
//    public void setType(TypeOfField type) {
//        this.type = type;
//    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "required")
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
