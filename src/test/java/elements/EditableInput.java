package elements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$x;

public class EditableInput {

    String label;
    String field = "//div[@id='undefinedGroup'][label[text()='%s']]//p";

    public EditableInput(String label) {
        this.label = label;
    }

    public void insert(String text) {
        SelenideElement inputField = $x(String.format(field, label));
        WebDriverRunner.driver().executeJavaScript("arguments[0].setAttribute('value', arguments[1])", inputField, text);
        //$x(String.format(field, label)).setValue(text);
    }

    public EditableInput clear() {
        $x(String.format(field, label)).clear();
        return this;
    }
}

