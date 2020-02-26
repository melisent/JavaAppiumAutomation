package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON= "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
       return this.waitForElementPresent(By.id(TITLE), "Cannot find articl title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot Find More Options",
                10);

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot fing 'Got it' tip overlay",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input for a reading list",
                5);


        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                (name_of_folder),
                "Cannot put text into articles folder title",
                5);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find OK",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, Cannot find X",
                5);
    }

    public void assertArticleTitlePresents ()

    {
        this.assertElementPresent(By.id(TITLE), "We didnt find any item");
    }

}
