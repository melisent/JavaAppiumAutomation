import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;



public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    //ex6
    @Test
    public void testArticleTitlePresent()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSub("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.assertArticleTitlePresents();
    }

    //ex 3
    @Test
    public void testFindIreland() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cant Find Wikipedia Input",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[(@text='Search…')]"),
                "Ireland",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cant find any results",
                15);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cant Cancel Search",
                5);


    }

    //ex5
    @Test
    public void testSaveTwoArticlesAndDeleteOne()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "cant find search",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[(@text='Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "cant find search",
                5);

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find Article Title",
                15);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot Find More Options",
                10);

        MainPageObject.waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot fing 'Got it' tip overlay",
                5);

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input for a reading list",
                5);

        String name_of_folder = "Learning Programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                (name_of_folder),
                "Cannot put text into articles folder title",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find OK",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, Cannot find X",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "cant find search",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[(@text='Search…')]"),
                "Ruby",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "cant find search",
                5);

        WebElement secondArticleTitle =  MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find Article Title",
                15);

        String second_article_title_before_deleting = secondArticleTitle.getAttribute("text");

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot Find More Options",
                10);

        MainPageObject.waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find existing article folder",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, Cannot find X",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my lists",
                5);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find new article folder",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find new article folder",
                5);

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find Saved Article");

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot Delete Saved Article",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot Find the Second Saved Article",
                5);

        WebElement secondArticleTitleAfterDeleting =  MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find second article after deleting first article");

        String second_article_title_after_deleting = secondArticleTitleAfterDeleting.getAttribute("text");

        assertEquals(
                "Second Article Title Changes After deleting First Article ",
                second_article_title_before_deleting,
                second_article_title_after_deleting

        );
    }


}

