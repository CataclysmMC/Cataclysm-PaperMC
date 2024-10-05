package symphony.cataclysm.components.player.totem;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import symphony.cataclysm.components.player.file.PlayerFileHelper;
import symphony.cataclysm.components.player.file.PlayerFileManager;

import java.io.File;
import java.io.IOException;

@Getter
public class PlayerTotemManager {

    private final YamlConfiguration configuration;
    private final File file;

    public PlayerTotemManager(Player player) {
        this(new PlayerFileManager(player));
    }

    public PlayerTotemManager(PlayerFileManager playerFileManager) {
        this.configuration = playerFileManager.getConfiguration();
        this.file = playerFileManager.getFile();
    }

    public void setPoppedTotems(int totemsPopped) {
        this.getConfiguration().set(PlayerFileHelper.getInformationConfigPath() + "totems-popped", totemsPopped);

        try {
            this.getConfiguration().save(this.getFile());
        } catch (IOException exception) {
            exception.fillInStackTrace();
        }
    }

    public int getPoppedTotems() {
        return this.getConfiguration().getInt(PlayerFileHelper.getInformationConfigPath() + "totems-popped");
    }

}
