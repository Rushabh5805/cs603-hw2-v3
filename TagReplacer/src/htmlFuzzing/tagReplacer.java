package htmlFuzzing;

import htmlFuzzing.spi.HtmlFuzzing;

import java.util.Random;

/**
 * tagReplacer class which implements the interface HtmlFuzzing
 * contains methods which will fuzz the string
 */
public class tagReplacer implements HtmlFuzzing {
    /**
     * getfuzz method which is implemented from the HtmlFuzzing interface will
     * fuzz the string by replacing some html tags with other tags.
     * @param code : the string containing some html code
     * @return a string which is modified by replacing a html tag with another
     */
    @Override
    public String getfuzz(String code) {
        String[] tags = {"<html>", "</html>", "<head>", "</head>", "<body>", "</body>","<p>","</p>","<b>", "</b>"};
        //,"<p>","</p>","<title>", "</title>"
        Random rand = new Random();
        int n = rand.nextInt(tags.length);
        int j = rand.nextInt(tags.length);
        code = code.replace(tags[n], ""+tags[j]);
        return code;
    }
}
