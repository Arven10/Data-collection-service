package models;

import javax.persistence.*;

/**
 * Created by Dorozhan on 06.05.2015.
 */

@Entity
@Table(name = "response")
@SequenceGenerator(name = "response_id_generator", sequenceName = "response_response_id_sequence",
        allocationSize = 1)
public class Response {
    private int id;
    private int fieldId;
    private String fieldName;
    private String responseName;

    @Id
    @GeneratedValue
    @Column(name = "response_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "field_id")
    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    @Column(name = "field_name")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Column(name = "response_name")
    public String getResponseName() {
        return responseName;
    }

    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }
}
