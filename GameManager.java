import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GameManager {

    private static final String CURRENT_VERSION = "1.0";
    private static final String DOWNLOAD_URL = "https://example.com/game/latest_version.zip";
    private static final String INSTALLATION_PATH = "path/to/installation";

    public void checkAndUpdateGame() {
        try {
            String latestVersion = checkLatestVersion();

            if (!latestVersion.equals(CURRENT_VERSION)) {
                System.out.println("Yeni sürüm bulundu. Güncelleniyor...");

                downloadAndExtractLatestVersion();
                System.out.println("Güncelleme tamamlandı.");

                // Burada oyunu başlatma veya diğer güncelleme işlemlerini gerçekleştirme
            } else {
                System.out.println("Oyun zaten güncel. Versiyon: " + CURRENT_VERSION);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String checkLatestVersion() throws IOException {
        URL url = new URL("https://example.com/game/version.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return reader.readLine();
        }
    }

    private void downloadAndExtractLatestVersion() throws IOException {
        URL url = new URL(DOWNLOAD_URL);

        try (InputStream in = url.openStream();
             ZipInputStream zis = new ZipInputStream(new BufferedInputStream(in))) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String filePath = INSTALLATION_PATH + File.separator + entry.getName();

                if (!entry.isDirectory()) {
                    extractFile(zis, filePath);
                } else {
                    Files.createDirectories(Paths.get(filePath));
                }
            }
        }
    }

    private void extractFile(ZipInputStream zis, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = zis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
    }
}
