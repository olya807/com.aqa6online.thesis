package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EditableInput {
    String label;
    String locator = "//*[contains(text(),'%s')]/parent::div/descendant::p";
    String parent = "//div[@id='undefinedGroup'][label[text()='%s']]/input";

    public EditableInput(String label) {
        this.label = label;
    }

    public void setFocus() {
        $x(String.format(parent, label)).click();
    }

    public void insert(String text) {
        setFocus();
        $x(String.format(locator, label)).shouldBe(Condition.visible).setValue(text);
    }

    public EditableInput clear() {
        SelenideElement element = $x(String.format(locator, label));
        element.click();
        element.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .clear();
        return this;
    }
}

