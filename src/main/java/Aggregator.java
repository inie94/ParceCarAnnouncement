import model.AutoRuStrategy;
import model.Provider;

import java.io.IOException;

public class Aggregator {
    public static void main(String[] args) throws IOException {
        Provider provider = new Provider(AutoRuStrategy.getInstance());
        Controller controller = new Controller(provider, "vesta");
        controller.generateOutputFile("testHtml.html", "C:\\Users\\anani\\Desktop");
    }
}
