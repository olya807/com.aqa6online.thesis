package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    public final static String endpoint = "project/%s";

    public SelenideElement getProjectNameHeader() {

        return $("p.header");
    }

    public SelenideElement getAlertMessage() {

        return $(".alert-message");
    }

    public SelenideElement getCreateSuiteButton() {

        return $("#create-suite-button");
    }

    public SelenideElement getSuiteNameInput() {

        return $("#name");
    }

    public SelenideElement getCreateNewSuiteButton() {

        return $(byText("Create new suite"));
    }

    public SelenideElement getSaveSuiteButton() {

        return $("#save-suite-button");
    }

    public String getProjectNameHeaderText(){

        return getProjectNameHeader().getText();
    }

    public ProjectPage clickCreateSuiteButton(){

        getCreateSuiteButton().click();
        return this;
    }

    public ProjectPage clickCreateNewSuiteButton(){

        getCreateNewSuiteButton().click();
        return this;
    }

    public ProjectPage setSuiteName(String suiteName){

        getSuiteNameInput().sendKeys(suiteName);
        return this;
    }

    public ProjectPage clickSaveSuiteButton(){

        getSaveSuiteButton().click();
        return this;
    }
}
