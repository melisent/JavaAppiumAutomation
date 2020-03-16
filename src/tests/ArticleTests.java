package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSub("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title);

    }

    @Test
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSub("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }

    //ex6
    @Test
    public void testArticleTitlePresent()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSub("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.assertArticleTitlePresents();
    }
}
