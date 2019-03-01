package htmlFuzzing;

/**
 * fuzzingClient class will basically call the methods of service provider
 * for modification of the html string and then clean and parsing it.
 */

public class fuzzingClient {

        public static void main(String[] args){
        htmlFuzzing.fuzzingService fs = htmlFuzzing.fuzzingService.getInstance();
        String fuzz = "<html> <head>  </head> <body> <p> <b> Hi </b> </p> </body> </html>";
        fs.singleModification(fuzz);
        fs.combinedModification(fuzz);
        fs.Clean(fuzz);
        //System.out.println(fs.hi(fuzz));


    }
}
