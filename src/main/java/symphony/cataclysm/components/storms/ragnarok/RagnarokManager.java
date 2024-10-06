package symphony.cataclysm.components.storms.ragnarok;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokEndEvent;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokStartEvent;
import symphony.utils.NumberUtils;

import java.io.IOException;
import java.util.Random;

public class RagnarokManager {
    private static @Getter @Setter int scheduledRagnarokDuration;

    public static void startRangarok() {
        RagnarokTask.runRagnarokProgresionTask();

        if (RagnarokManager.isRagnarokActivated()) for (Player player : Bukkit.getOnlinePlayers()) RagnarokBossbar.removeRagnarokBossbar(player);
        RagnarokBossbar.setUpRagnarokBossbar();
        for (Player player : Bukkit.getOnlinePlayers()) RagnarokBossbar.displayRagnarokBossbar(player);

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile());

        configuration.set(RagnarokHelper.getRagnarokLevelConfigPath(), RagnarokManager.getRagnarokLevel() + 1);

        try {
            configuration.save(RagnarokHelper.getRagnarokEventFile());
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + RagnarokHelper.getRagnarokEventFile().getName(), exception);
        }

        RagnarokManager.setRagnarokTotalDuration(RagnarokManager.getScheduledRagnarokDuration());
        RagnarokManager.setRagnarokCurrentProgress(RagnarokManager.getScheduledRagnarokDuration());

        Bukkit.getPluginManager().callEvent(new RagnarokStartEvent(RagnarokManager.getRagnarokTotalDuration()));
    }

    public static void stopRagnarok() {
        Bukkit.getScheduler().cancelTask(RagnarokTask.getRagnarokProgresionTask());

        Bukkit.getPluginManager().callEvent(new RagnarokEndEvent());

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile());

        configuration.set(RagnarokHelper.getRagnarokLevelConfigPath(), 0);

        try {
            configuration.save(RagnarokHelper.getRagnarokEventFile());
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + RagnarokHelper.getRagnarokEventFile().getName(), exception);
        }

        RagnarokManager.setRagnarokTotalDuration(0);
        RagnarokManager.setRagnarokCurrentProgress(0);

        for (Player player : Bukkit.getOnlinePlayers()) RagnarokBossbar.removeRagnarokBossbar(player);
        RagnarokBossbar.setDecoratedRagnarokBossbar(null);
        RagnarokBossbar.setTimerRagnarokBossbar(null);
    }

    public static void setRagnarokTotalDuration(int totalDuration) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile());

        configuration.set(RagnarokHelper.getRagnarokTotalDuration(), totalDuration);

        try {
            configuration.save(RagnarokHelper.getRagnarokEventFile());
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + RagnarokHelper.getRagnarokEventFile().getName(), exception);
        }
    }

    public static void setRagnarokCurrentProgress(int progress) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile());

        configuration.set(RagnarokHelper.getRagnarokCurrentProgress(), progress);

        try {
            configuration.save(RagnarokHelper.getRagnarokEventFile());
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + RagnarokHelper.getRagnarokEventFile().getName(), exception);
        }

        if (RagnarokBossbar.getDecoratedRagnarokBossbar() != null && RagnarokBossbar.getTimerRagnarokBossbar() != null) {
            RagnarokBossbar.updateRagnarokBossbar();
        }
    }

    public static int getRagnarokTotalDuration() {
        return YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile()).getInt(RagnarokHelper.getRagnarokTotalDuration());
    }

    public static int getRagnarokCurrentProgress() {
        return YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile()).getInt(RagnarokHelper.getRagnarokCurrentProgress());
    }

    public static int getRagnarokLevel() {
        return YamlConfiguration.loadConfiguration(RagnarokHelper.getRagnarokEventFile()).getInt(RagnarokHelper.getRagnarokLevelConfigPath());
    }

    public static String getRagnarokFormattedDuration() {
        return NumberUtils.formatSeconds(getRagnarokCurrentProgress(), (getRagnarokCurrentProgress() >= 3600));
    }

    public static boolean isRagnarokActivated() {
        return RagnarokManager.getRagnarokLevel() != 0;
    }
}
