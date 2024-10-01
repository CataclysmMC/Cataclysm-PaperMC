package symphony.cataclysm.components.player.death.message;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import symphony.cataclysm.components.player.file.PlayerFileManager;

import java.io.File;
import java.io.IOException;

@Getter
public class DeathMessageManager {
    private final File file;
    private final YamlConfiguration configuration;

    public DeathMessageManager(String username) {
        PlayerFileManager playerFileManager = new PlayerFileManager(username);
        this.file = playerFileManager.getFile();
        this.configuration = playerFileManager.getConfiguration();
    }

    public void setDeathMessage(String deathMessage) {
        this.getConfiguration().set(DeathMessageHelper.getDeathMessageConfigPath(), deathMessage);

        try {
            this.configuration.save(this.file);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getDeathMessage() {
        return this.getConfiguration().getString(DeathMessageHelper.getDeathMessageConfigPath());
    }
}
