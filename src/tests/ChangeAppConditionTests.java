package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

import java.time.Duration;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeOrientationOnSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSub("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String titleBeforeRotation =  ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String titleAfterRotation =  ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after screen rotation",
                titleBeforeRotation,
                titleAfterRotation);

        this.rotateScreenPortrait();

        String titleAfterSecondRotation =  ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after screen rotation",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(Duration.ofMillis(200));
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
