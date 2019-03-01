package htmlFuzzing;

import htmlFuzzing.spi.HtmlFuzzing;

import java.util.Random;

/**
 * tagRemover class which implements the interface HtmlFuzzing
 * contains methods which will fuzz the string
 */
public class tagRemover implements HtmlFuzzing {
    /**
     * getfuzz method which is implemented from the HtmlFuzzing interface will
     * fuzz the string by removing some html tags from the html string.
     * @param code : the string containing some html code
     * @return a string which is modified by removing a html tag
     */
    @Override
    public String getfuzz(String code) {
        String[] tags = {"<html>", "</html>", "<head>", "</head>", "<body>", "</body>","<p>","</p>","<b>", "</b>"};
        Random rand = new Random();
        int n = rand.nextInt(tags.length);
        code = code.replace(tags[n], " ");
        //System.out.println(code);
        return code;
    }
}
