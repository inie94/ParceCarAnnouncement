import ca.CarAd;
import model.Provider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Controller {
    private Provider provider;
    private List<CarAd> carAds;
    private String searchString;

    public Controller(Provider provider, String searchString) {
        this.provider = provider;
        this.searchString = searchString;
        this.carAds = provider.getAds(searchString);
    }

    public void printProviderAds() {
        carAds.forEach(carAd -> System.out.println(carAd.toString()));
    }

    public List<CarAd> getCarAds() {
        if (carAds.isEmpty()) throw new NullPointerException();
        return carAds;
    }

    public void generateOutputFile(String fileName, String dir) throws IOException {
        Path path = Paths.get(dir + "\\" + fileName);
        if (!path.toFile().exists()) {
            Files.createFile(path);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            writer.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\t<head>\n" +
                    "\t\t<meta charset=\"utf-8\">\n" +
                    "\t\t<title>Листинг объявлений</title>\n" +
                    "\t</head>\n" +
                    "\t<body>\n"+
                    "\t\t<h3><strong>Список объявлений по запросу: \"" + searchString + "\"</strong></h3>");

            for (CarAd carAd: getCarAds()) {
                writer.write(getAdContent(carAd) + "\n");
            }
            writer.write("\t</body>\n" +
                    "</html>");
        }
    }

    private String getAdContent(CarAd carAd) {
        return ("\t\t<p>\n" +
                "\t\t\t<dl>\n" +
                "\t\t\t\t<dt>\n" +
                "\t\t\t\t\t<a href=\"" + carAd.getUrl() + "\"><strong>" + carAd.getTitle() + "</strong></a>.\n" + carAd.getPrice() +
                "\t\t\t\t</dt>\n" +
                "\t\t\t\t\t<dd>Год выпуска: " + carAd.getCar().getYearOfIssue() + "</dd>\n" +
                "\t\t\t\t\t<dd>Двигатель: " + carAd.getCar().getEngine() + "</dd>\n" +
                "\t\t\t\t\t<dd>Коробка: " + carAd.getCar().getTransmission() + "</dd>\n" +
                "\t\t\t\t\t<dd>Привод: " + carAd.getCar().getDriveUnit() + "</dd>\n" +
                "\t\t\t\t\t<dd>Кузов: " + carAd.getCar().getBody() + "</dd>\n" +
                "\t\t\t\t\t<dd>Дополнительная информация: " + carAd.getCar().getAdditionInfo() + "</dd>\n" +
                "\t\t\t</dl>" +
                "\t\t</p>");


    }
}
