package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Dorozhan on 08.05.2015.
 */

@Entity
@Table(name = "option")
@SequenceGenerator(name = "option_id_generator", sequenceName = "option_option_id_sequence", allocationSize = 1)
public class Option {
    private int id;
    private String text;
    private Field field;

    public Option() {
    }

    public Option(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue
    @Column(name = "option_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "field_id", nullable = false)
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
