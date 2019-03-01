package htmlFuzzing;

import htmlFuzzing.spi.HtmlFuzzing;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.util.*;
import java.util.stream.Collectors;

/**
 * fuzzingService class implements the singleton design pattern.
 * fuzzingService class makes has different methods for the fuzzing.
 */
public class fuzzingService {
    private static fuzzingService serviceProvider;
    private ServiceLoader<HtmlFuzzing> loader;
    private List<HtmlFuzzing> tagReplacerRemover;
    private List<HtmlFuzzing> scriptTagInserter;
    private int iterations = 100;

    /**
     * Constructor of fuzzingService class which will initiate the lists and the loader
     */
    public fuzzingService(){
        loader = ServiceLoader.load(HtmlFuzzing.class);
        loader.reload();
        tagReplacerRemover = loader.stream().filter(serviceProvider-> serviceProvider.type().getTypeName().equals("htmlFuzzing.tagRemover")||serviceProvider.type().getTypeName().equals("htmlFuzzing.tagReplacer")).map(ServiceLoader.Provider::get).collect(Collectors.toList());
        scriptTagInserter = loader.stream().filter(serviceProvider -> serviceProvider.type().getTypeName().equals("htmlFuzzing.scriptTagInserter")).map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    /**
     * getInstance method, which enables other classes controlled access to the fuzzingService member variable serviceProvider.
     * @return object of fuzzingService class
     */
    public static synchronized fuzzingService getInstance(){
        if(serviceProvider == null){
            serviceProvider = new fuzzingService();
        }
        return serviceProvider;
    }

    /**
     * singleModification method calls one randomly between the tagRemover and tagReplacer
     * which modifies the string and then Jsoup will parse that string and returns the
     * correct the html code.
     * @param tag : the string containing some html code
     */
    public void singleModification(String tag) {
        for (int j = 0; j < iterations; j++) {
            try {

                Random rand = new Random();
                int n = rand.nextInt(tagReplacerRemover.size());
                HtmlFuzzing htmlFuzzingObj = tagReplacerRemover.get(n);
                String fuzzedString = htmlFuzzingObj.getfuzz(tag);
                Document doc = Jsoup.parse(fuzzedString);
                System.out.println(htmlFuzzingObj);
                System.out.println(fuzzedString);
                System.out.println(doc);
            } catch (ServiceConfigurationError serviceError) {
                serviceProvider = null;
                serviceError.printStackTrace();
                break;

            }
            System.out.println("---------------------------------------------");
            System.out.println("---------------------------------------------");
        }
    }

    /**
     * multipleModification method calls randomly between both the tagRemover and tagReplacer
     * which modifies the string and then Jsoup will parse that string and returns the
     * correct the html code.
     * @param tag : the string containing some html code
     */
    public void combinedModification(String tag) {
        for (int k = 0; k < iterations; k++) {
            try {
                Random rand = new Random();
                for (int i = 0; i < 2; i++) {
                    String fuzzedString = tag;
                    int n = rand.nextInt(tagReplacerRemover.size());
                    HtmlFuzzing htmlFuzzingObj = tagReplacerRemover.get(n);
                    fuzzedString = htmlFuzzingObj.getfuzz(fuzzedString);
                    Document doc = Jsoup.parse(fuzzedString);
                    System.out.println(htmlFuzzingObj);
                    System.out.println(fuzzedString);
                    System.out.println(doc);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceProvider = null;
                serviceError.printStackTrace();
                break;

            }
            System.out.println("---------------------------------------------");
            System.out.println("---------------------------------------------");
        }
    }

    /**
     * Clean method will try to avoid the cross scripting attacks and
     * cleans the html code by removing the malicious scripts.
     * @param tag : the string containing some html code
     */

    public void Clean(String tag){
        for(int l=0; l<iterations;l++) {
            try {
                String fuzzedString = tag;
                HtmlFuzzing htmlFuzzingObj = scriptTagInserter.get(0);
                fuzzedString = htmlFuzzingObj.getfuzz(fuzzedString);
                Document doc = Jsoup.parse(Jsoup.clean(fuzzedString, Whitelist.basic()));
                System.out.println(htmlFuzzingObj);
                System.out.println(fuzzedString);
                System.out.println(doc);
            }
            catch (ServiceConfigurationError serviceError) {
                serviceProvider = null;
                serviceError.printStackTrace();
                break;
            }


        }
    }

}
