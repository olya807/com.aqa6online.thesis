package baseEntities;

public abstract class BasePage {

    protected abstract void openPage();

    public BasePage(boolean openPageByUrl) {

        if (openPageByUrl) {
            openPage();
        }
    }
}
