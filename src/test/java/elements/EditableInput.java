package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EditableInput {
    String label;
    String locator = "//*[contains(text(),'%s')]/parent::div/descendant::p";
    String parent = "//*[contains(text(),'%s')]/following-sibling::input";

    public EditableInput(String label) {
        this.label = label;
    }

    public void clickOnVisibleField() {
        $x(String.format(parent, label)).click();
    }

    public void insert(String text) {
        clickOnVisibleField();
        $x(String.format(parent, label)).setValue(text);
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

