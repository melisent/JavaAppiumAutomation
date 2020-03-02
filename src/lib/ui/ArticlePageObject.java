package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON= "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST = "xpath:/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        LIST_FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getListFolderXpathByName(String name_of_folder)
    {
        return LIST_FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    /*TEMPLATES METHODS*/

    public WebElement waitForTitleElement()
    {
       return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot Find More Options",
                10);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot fing 'Got it' tip overlay",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input for a reading list",
                5);


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                (name_of_folder),
                "Cannot put text into articles folder title",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find OK",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, Cannot find X",
                5);
    }

    public void assertArticleTitlePresents ()

    {
        this.assertElementPresent(TITLE, "We didnt find any item");
    }

    public void addArticleToExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot Find More Options", 10);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST, "Cannot find option to add article to reading list", 5);

        String lists_folder_xpath = getListFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                lists_folder_xpath,
                "Cannot find existing article folder",
                5);
    }
}
