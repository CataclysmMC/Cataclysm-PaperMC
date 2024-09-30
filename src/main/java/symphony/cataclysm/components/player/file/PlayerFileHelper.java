package symphony.cataclysm.components.player.file;

import lombok.Getter;
import lombok.Setter;
import symphony.cataclysm.Cataclysm;

import java.io.File;

public class PlayerFileHelper {
    private static final @Getter String informationConfigPath = "information.";
    private static @Getter @Setter File playersFolder;

    public static void createPlayersFolder() {
        PlayerFileHelper.setPlayersFolder(new File(Cataclysm.getInstance().getDataFolder(), "Players"));

        boolean isPlayersFolderCreated = PlayerFileHelper.getPlayersFolder().mkdir();

        if (!isPlayersFolderCreated) Cataclysm.logMessage("El folder " + PlayerFileHelper.getPlayersFolder().getName() + " ha sido creado exitosamente.");
    }
}
