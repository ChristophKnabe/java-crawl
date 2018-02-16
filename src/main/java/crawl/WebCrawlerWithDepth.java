package crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

/**Simple Java Command Line Web Crawler using JSoup.
 * Source code taken from chapter 3 of <a href="https://www.mkyong.com/java/jsoup-basic-web-crawler-example/">jsoup – Basic web crawler example</a>.
 * Styled more constant and configurable on the command line.
 */
public class WebCrawlerWithDepth {

    private final int maxDepth;
    private final HashSet<String> links = new HashSet<>();

    /**Creates a web crawler crawling up to the given maximum link depth.*/
    public WebCrawlerWithDepth(final int maxDepth){
        this.maxDepth = maxDepth;
    }

    public void getPageLinks(final String URL, final int depth) {
        if (links.contains(URL)){ //URL already known
            return;
        }
        if (depth > maxDepth) { //Do not go too deep!
            return;
        }
        System.out.println(">> Depth: " + depth + " [" + URL + "]");
        try {
            links.add(URL);

            final Document document = Jsoup.connect(URL).get();
            final Elements linksOnPage = document.select("a[href]");

            for (final Element page : linksOnPage) {
                getPageLinks(page.attr("abs:href"), depth+1);
            }
        } catch (final IOException e) {
            System.err.println("For '" + URL + "': " + e.getMessage());
        }
    }

    public static void main(final String[] args) {
        final String startURL = args.length>0 ? args[0] : "http://public.beuth-hochschule.de/~knabe/";
        final int maxDepth = args.length>1 ? Integer.parseInt(args[1]) : 1;
        new WebCrawlerWithDepth(maxDepth).getPageLinks(startURL, 0);
    }

}

