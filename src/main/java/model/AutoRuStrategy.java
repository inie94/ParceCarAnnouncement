package model;

import ca.Car;
import ca.CarAd;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.time.LocalTime;
import java.util.*;

public class AutoRuStrategy implements Strategy {
    private static String URL_FORMAT = "https://auto.ru/rostov-na-donu/cars/vaz/vesta/all/?query=%s&from=searchline&page=%d";

    private static final AutoRuStrategy INSTANCE = new AutoRuStrategy();

    private WebDriver driver;

    private AutoRuStrategy() {
        System.setProperty("phantomjs.binary.path", "D:\\anani\\Documents\\JavaFiles\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36";
        System.setProperty("phantomjs.page.settings.userAgent", userAgent);

        this.driver = new PhantomJSDriver();
    }

    public static AutoRuStrategy getInstance() {
        return INSTANCE;
    }

    private void waitInterval(long timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CarAd> getAds(String searchString) {
        List<CarAd> carAds = new ArrayList<>();
        int page = 0;
//        LocalTime timeStart = LocalTime.now();
        int i = 0;
        do {
            driver.get(String.format(URL_FORMAT, searchString, page));
//            LocalTime timePageLoad = LocalTime.now();
//            System.out.println(timePageLoad.compareTo(timeStart));
            waitInterval(1000);

            List<WebElement> elements = driver.findElements(new By.ByClassName("ListingItem-module__description"));
            waitInterval(1000);
            if (elements.isEmpty()) break;
//            System.out.println(elements.size() + "\n");

            for (WebElement element: elements) {
//                LocalTime timeStartReadAd = LocalTime.now();
                String title = element.findElement(new By.ByTagName("h3")).getText();
                String price = element.findElement(new By.ByClassName("ListingItem-module__columnCellPrice")).getText()
                        .split("\\n")[0];
                String location = element.findElements(new By.ByClassName("ListingItem-module__columnRow"))
                        .get(1)
                        .findElements(new By.ByTagName("span"))
                        .get(0)
                        .getText().split("\\(")[0].split("\\d")[0].trim();
                // условие наличия поля продовца
                String seller;
                if (element.findElement(new By.ByClassName("ListingItem-module__columnCellKmAge"))
                        .findElements(new By.ByTagName("a")).size() != 0) {
                    seller = element.findElement(new By.ByClassName("ListingItem-module__columnCellKmAge"))
                            .findElement(new By.ByTagName("a")).getText();
                } else {
                    seller = "";
                }
                //-----------------
                String url = element.findElement(new By.ByTagName("h3")).findElement(new By.ByTagName("a"))
                        .getAttribute("href");
                String year = element.findElement(new By.ByClassName("ListingItem-module__year")).getText();
                List<String> info = new ArrayList<>();
                element.findElements(new By.ByClassName("ListingItemTechSummaryDesktop__column"))
                        .forEach(webElement -> info.addAll(Arrays.asList((webElement.getText().split("\\n")))));


//                System.out.println("\n" + i);
//                System.out.println(
//                        title + "\n" +
//                        price + "\n" +
//                        location + "\n" +
//                        seller + "\n" +
//                        url + "\n" +
//                        year);


                Car car = new Car();
                car.setYearOfIssue(year);
                car.setEngine(info.get(0));
                car.setTransmission(info.get(1));
                car.setBody(info.get(2));
                car.setDriveUnit(info.get(3));
                car.setAdditionInfo(info.get(4));

                CarAd carAd = new CarAd();
                carAd.setCar(car);
                carAd.setTitle(title);
                carAd.setPrice(price);
                carAd.setLocation(location);
                carAd.setSeller(seller);
                carAd.setUrl(url);

                carAds.add(carAd);
//                i++;
            }
//            LocalTime timeEndPageRead = LocalTime.now();
//            System.out.println("\nTime read all page: " + (timeEndPageRead.toSecondOfDay() - timePageLoad.toSecondOfDay()));
            page++;
            break;
        } while (true);
//        LocalTime timeEnd = LocalTime.now();
//        System.out.println("All time: " + (timeEnd.toSecondOfDay() - timeStart.toSecondOfDay()));
        driver.quit();
        return carAds;
    }
}
