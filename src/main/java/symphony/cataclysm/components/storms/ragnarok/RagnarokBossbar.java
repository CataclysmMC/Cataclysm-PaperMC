package symphony.cataclysm.components.storms.ragnarok;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class RagnarokBossbar {
    private static @Getter @Setter BossBar decoratedRagnarokBossbar;
    private static @Getter @Setter BossBar timerRagnarokBossbar;

    public static void setUpRagnarokBossbar() {
        RagnarokBossbar.setDecoratedRagnarokBossbar(BossBar.bossBar(
                MiniMessage.miniMessage().deserialize("\uE666"),
                1.0F,
                BossBar.Color.BLUE,
                BossBar.Overlay.PROGRESS)
        );
        RagnarokBossbar.setTimerRagnarokBossbar(BossBar.bossBar(
                MiniMessage.miniMessage().deserialize("<#ccd9d9>" + RagnarokManager.getRagnarokFormattedDuration()),
                1.0F,
                BossBar.Color.YELLOW,
                BossBar.Overlay.PROGRESS)
        );
    }

    public static void updateRagnarokBossbar() {
        RagnarokBossbar.getDecoratedRagnarokBossbar().progress((float) RagnarokManager.getRagnarokCurrentProgress() / RagnarokManager.getRagnarokTotalDuration());
        RagnarokBossbar.getTimerRagnarokBossbar().name(MiniMessage.miniMessage().deserialize("<#ccd9d9>" + RagnarokManager.getRagnarokFormattedDuration()));
    }

    public static void displayRagnarokBossbar(Player player) {
        RagnarokBossbar.getDecoratedRagnarokBossbar().addViewer(player);
        RagnarokBossbar.getTimerRagnarokBossbar().addViewer(player);
    }

    public static void removeRagnarokBossbar(Player player) {
        RagnarokBossbar.getDecoratedRagnarokBossbar().removeViewer(player);
        RagnarokBossbar.getTimerRagnarokBossbar().removeViewer(player);
    }
}
