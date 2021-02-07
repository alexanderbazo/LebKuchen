package utils;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Diese Klasse unterstützt Sie bei der Integration zusätzlicher Schriftarten in Ihre GraphicsApp-Umgebung. Verwenden Sie die
 * Methode "loadFonts", um zu Beginn zusätzliche TrueType-Schriftarten aus einem Verzeichnis innerhalb des Projekts zu laden.
 * Diese Schriftarten können im Anschluss in Label-Instanzen verwendet werden.
 * <p>
 * Beispiel:
 * <p>
 * // z.B. in initialize()
 * FontHelper.loadFonts("data/fonts");
 * <p>
 * // Verwenden der geladenen Schriftarten
 * Label label = new Label(0, 0, "Hello World");
 * label.setFont("CustomFont"); // Hier muss der FontName der zusätzlich geladenen Schriftart angegeben werden
 * <p>
 * Eine Liste der per "loadFonts" geladenen Schriftarten können Sie über den Aufruf der Methode "printLoadedFonts()"
 * auf der Konsole ausgeben:
 * <p>
 * FontHelper.printLoadedFonts();
 */

public abstract class FontLoader {

    private static final ArrayList<Font> customFontsLoaded = new ArrayList<>();

    /**
     * Versucht, alle TrueType-Schriftarten (Identifikation über die Dateiendung ttf) aus dem angegebenen Ordner zu laden und
     * in der laufenden Anwendung bereitzustellen.
     *
     * @param pathToFonts Pfad zum Ordner mit den neuen Schriftarten
     */
    public static void loadFonts(String pathToFonts) {
        File fontDir = new File(pathToFonts);
        File[] fontFiles = fontDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ttf");
            }
        });
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (File fontFile : fontFiles) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                ge.registerFont(font);
                customFontsLoaded.add(font);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gibt true zurück, wenn die Schriftart mit dem angegebenen FontName in der laufenden Anwendung zur Verfügung steht.
     *
     * @param name FontName der zu prüfenden Schriftart
     * @return true, wenn Schriftart verwendet werden kann
     */
    public static boolean checkIfFontIsLoaded(String name) {
        for (Font customFont : customFontsLoaded) {
            if (customFont.getFontName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt eine Liste aller über diese Klasse geladenen, zusätzlichen Schriftarten auf der Konsole aus.
     */
    public static void printLoadedFonts() {
        System.out.println("### Available Fonts (FontName & Family) ###");
        for (int i = 0; i < customFontsLoaded.size(); i++) {
            System.out.println("#" + i + "\t" + customFontsLoaded.get(i).getFontName() + " (" + customFontsLoaded.get(i).getFamily() + ")");
        }
    }
}
