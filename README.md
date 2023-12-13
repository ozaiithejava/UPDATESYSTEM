# UPDATESYSTEM
a basic update sys in java dowland a new zip and ext.

## Replace this
```Java
  URL url = new URL("https://example.com/game/version.txt");
private static final String CURRENT_VERSION = "1.0"; // Güncel sürüm numarası
    private static final String DOWNLOAD_URL = "https://example.com/game/latest_version.zip"; // Güncel sürümün indirme bağlantısı
    private static final String INSTALLATION_PATH = "path/to/installation"; // Oyunun yüklü olduğu dizin
```



## Usage:
```java
public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.checkAndUpdateGame();
    }
}
```
