package symphony.cataclysm.components.storms.ragnarok;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokEndEvent;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokStartEvent;
import symphony.cataclysm.components.time.TimeHelper;

import java.io.IOException;
import java.util.Random;

public class RagnarokManager {

    public static void startRangarok() {
        int duration = new Random().nextInt(1800, 3000) + RagnarokManager.getRagnarokCurrentProgress();

        Bukkit.getPluginManager().callEvent(new RagnarokStartEvent(duration));

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile());

        configuration.set(RagnarokHelper.getRagnarokLevelConfigPath(), RagnarokManager.getRagnarokLevel() + 1);
        configuration.set(RagnarokHelper.getRagnarokCurrentProgress(), duration);

        try {
            configuration.save(RagnarokHelper.getRagnarokEventFile());
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + RagnarokHelper.getRagnarokEventFile().getName(), exception);
        }
    }

    public static void stopRagnarok() {
        Bukkit.getPluginManager().callEvent(new RagnarokEndEvent());

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile());

        configuration.set(RagnarokHelper.getRagnarokLevelConfigPath(), 0);

        try {
            configuration.save(RagnarokHelper.getRagnarokEventFile());
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + RagnarokHelper.getRagnarokEventFile().getName(), exception);
        }
    }

    public static int getRagnarokCurrentProgress() {
        return YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile()).getInt(RagnarokHelper.getRagnarokCurrentProgress());
    }

    public static int getRagnarokLevel() {
        return YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile()).getInt(RagnarokHelper.getRagnarokLevelConfigPath());
    }

    public static boolean isRagnarokActivated() {
        return RagnarokManager.getRagnarokLevel() != 0;
    }
}
