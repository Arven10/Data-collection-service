package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Field;
import models.Option;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;


public class ControllerDC extends Controller {
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result saveField() {
        JsonNode jsonField = request().body().asJson();
        if (jsonField == null) {
            return badRequest();
        }
        Field field = Json.fromJson(jsonField, Field.class);
        JPA.em().persist(field);
        if (field.getOptions() != null) {
            for (Option option: field.getOptions()) {
                option.setField(field);
                JPA.em().persist(option);
            }
        }
        return ok();
    }

    @Transactional(readOnly = true)
    public static Result getAllFields() {
        List fieldList = JPA.em().createQuery("SELECT fields from Field fields").getResultList();
        return ok(Json.toJson(fieldList));
    }

    @Transactional
    public static Result deleteField(int id) {
        Object field = JPA.em()
                .createQuery("SELECT field from Field field where field.id = :id")
                .setParameter("id", id).getSingleResult();
        if (field == null) {
            return badRequest();
        }
        JPA.em().remove(field);
        return ok();
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateField(int id) {
        JsonNode jsonField = request().body().asJson();
        if (jsonField == null) {
            return badRequest();
        }
        Field field = Json.fromJson(jsonField, Field.class);
        Field updateField = (Field) JPA.em()
                .createQuery("SELECT field from Field field where field.id = :id")
                .setParameter("id", id).getSingleResult();
        if (updateField == null) {
            return badRequest();
        }
        updateField.setActive(field.isActive());
        updateField.setLabel(field.getLabel());
        updateField.setType(field.getType());
        updateField.setOptions(field.getOptions());
        updateField.setRequired(field.isRequired());
        return ok();
    }

    @Transactional(readOnly = true)
    public static Result getFieldById(int id) {
        Object field = JPA.em()
                .createQuery("SELECT field from Field field where field.id = :id")
                .setParameter("id", id).getSingleResult();
        System.out.println(Json.toJson(field));
        return ok(Json.toJson(field));

    }

    @Transactional(readOnly = true)
    public static Result getAllResponses() {
        List fieldList = JPA.em().createQuery("SELECT fields from Response fields").getResultList();
        return ok(Json.toJson(fieldList));
    }
}
