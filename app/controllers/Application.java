package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result main(String any) {
        return ok(main.render());
    }

    public static Result fieldsList() {
        return ok(fieldsList.render());
    }

    public static Result fieldCreate() {
        return ok(fieldCreate.render());
    }

    public static Result fieldEdit() {
        return ok(fieldEdit.render());
    }

    public static Result responsesList() {
        return ok(responsesList.render());
    }

}
