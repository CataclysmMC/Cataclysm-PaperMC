package symphony.cataclysm.components.player.file;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import symphony.cataclysm.Cataclysm;

import java.io.File;
import java.io.IOException;

@Getter
public class PlayerFileManager {
    private final File file;
    private final YamlConfiguration configuration;

    public PlayerFileManager(String nickname) {
        this.file = new File(PlayerFileHelper.getPlayersFolder(), nickname + ".yml");

        boolean doesPlayerFileExists;
        try {
            doesPlayerFileExists = this.file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!doesPlayerFileExists) Cataclysm.logMessage("Se ha creado un archivo para el jugador: " + nickname);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public PlayerFileManager(Player player) {
        this(player.getName());
    }

}
