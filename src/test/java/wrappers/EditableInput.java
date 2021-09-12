package wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class EditableInput {
    String label;
    String locator = "//*[contains(text(),'%s')]/parent::div/descendant::p";
    String parent = "//*[contains(text(),'%s')]/following-sibling::input/preceding-sibling::div";

    public EditableInput(String label) {
        this.label = label;
    }

    public void setFocus() {
        $x(String.format(parent, label)).click();
    }

    public void insert(String text) {
        setFocus();
        $x(String.format(locator, label)).shouldBe(visible).setValue(text);
    }

    public EditableInput clear() {
        SelenideElement element = $x(String.format(locator, label));
        element.click();
        element.shouldBe(visible).clear();
        return this;
    }

    public void shouldHave(String text) {
        $x(String.format(locator, label)).shouldHave(Condition.exactText(text));
    }
}

