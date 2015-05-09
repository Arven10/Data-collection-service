package models;

/**
 * Created by Dorozhan on 30.04.2015.
 */
public enum TypeOfField {
    SINGLE_LINE_TEXT("SINGLE_LINE_TEXT"),
    MULTI_LINE_TEXT("MULTI_LINE_TEXT"),
    RADIO_BUTTON("RADIO_BUTTON"),
    CHECK_BOX("CHECK_BOX"),
    COMBO_BOX("COMBO_BOX"),
    DATE("DATE")
    ;

    private final String text;

    private TypeOfField(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
