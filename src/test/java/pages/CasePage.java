package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import endpoints.UiEndpoints;
import models.TestCase;
import elements.Button;
import elements.Dropdown;
import elements.EditableInput;
import elements.Input;

import static com.codeborne.selenide.Selenide.*;

public class CasePage extends BasePage {

    public CasePage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public SelenideElement getFileUploadField() {
        return $("#file");
    }


    private SelenideElement getSubmitBtn() {
        return $("[type='submit']");
    }

    public String getFileUploadFieldValue() {

        return getFileUploadField().getValue();
    }

    public CasePage fillCaseForm(TestCase testCase) {
        new Input("Title").clear().sendValue(testCase.getTitle());
        new EditableInput("Description").clear().insert(testCase.getDescription());
        new Dropdown("Status").setDropdown(testCase.getStatus());
        new Dropdown("Severity").setDropdown(testCase.getSeverity());
        new Dropdown("Priority").setDropdown(testCase.getPriority());
        new Dropdown("Type").setDropdown(testCase.getType());
        new Dropdown("Layer").setDropdown(testCase.getLayer());
        new Dropdown("Is Flaky").setDropdown(testCase.getIsFlaky());
        new Dropdown("Behavior").setDropdown(testCase.getBehavior());
        new Dropdown("Automation status").setDropdown(testCase.getAutomationStatus());
        new EditableInput("Post-conditions").clear().insert(testCase.getPostconditions());
        new EditableInput("Pre-conditions").clear().insert(testCase.getPreconditions());
        return this;

    } public CasePage updateCase(TestCase testCase) {
        new Dropdown("Layer").setDropdown(testCase.getLayer());
        new EditableInput("Pre-conditions").clear().insert(testCase.getPreconditions());
        return this;
    }

    public ProjectPage clickSaveButton() {
        new Button("Save").click();
        return new ProjectPage(false, UiEndpoints.PROJECT);
    }

    public ProjectPage submitBtnClick() {

        getSubmitBtn().click();
        return new ProjectPage(false, UiEndpoints.PROJECT);
    }


}
