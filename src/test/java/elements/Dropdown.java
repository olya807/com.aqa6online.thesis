package elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {

    String label;
    String dropdownBy = "//*[text()='%s']/parent::div/div[contains(@class, 'container')]";
    String dropdownOptionsBy = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";
    String selectedOptionBy = "//*[text()='%s']/parent::div/div[contains(@class, 'container')]/descendant::div[contains(text(),'%s')]";

    public Dropdown(String label) {
        this.label = label;
    }

    public void shouldHave(String text) {
        $x(String.format(selectedOptionBy, label, text)).shouldHave(Condition.exactText(text));
    }

    public Dropdown setDropdown(String optionName) {
        $x(String.format(dropdownBy, label)).click();
        $x(String.format(dropdownOptionsBy, optionName)).shouldBe(Condition.visible).click();
        return this;
    }
}

