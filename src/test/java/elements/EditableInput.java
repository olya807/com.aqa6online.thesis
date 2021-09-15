package elements;

import static com.codeborne.selenide.Selenide.$x;

public class EditableInput {

    String label;
    String field = "//div[@id='undefinedGroup'][label[text()='%s']]//p";

    public EditableInput(String label) {
        this.label = label;
    }

    public void insert(String text) {
        $x(String.format(field, label)).setValue(text);
    }

    public EditableInput clear() {
        $x(String.format(field, label)).clear();
        return this;
    }
}

