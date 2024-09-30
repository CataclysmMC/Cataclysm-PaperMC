package symphony.cataclysm.components.storms.ragnarok;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import symphony.cataclysm.Cataclysm;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokEndEvent;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokStartEvent;
import symphony.utils.ChatMessenger;

public class RagnarokListener implements Listener {

    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (!RagnarokManager.isRagnarokActivated()) return;

        Player player = event.getPlayer();
        RagnarokBossbar.displayRagnarokBossbar(player);
    }

    @EventHandler
    private void onRagnarokStartEvent(RagnarokStartEvent event) {
        int durationInSeconds = event.getDuration();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.showTitle(Title.title(
                    MiniMessage.miniMessage().deserialize("<#BD9C5E><obf>||<reset> <#49869B>Ragnarök <#BD9C5E><obf>||<reset>"),
                    MiniMessage.miniMessage().deserialize("<#a5a5a5>Duración: " + RagnarokManager.getRagnarokFormattedDuration())
            ));

            ChatMessenger.sendCataclysmMessage(player, "<gradient:#7999a6:#5f8abc>¡El Ragnarök nubla los cielos!</gradient>");

            player.playSound(player, "cataclysm.ragnarok.start", 1, 0.95F);
            player.playSound(player, Sound.BLOCK_END_PORTAL_SPAWN, 1.15F, 0.57F);
            player.playSound(player, Sound.ENTITY_SKELETON_HORSE_DEATH, 1.15F, 0.66F);

            Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> player.playSound(player, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1.15F, 0.5F), 20);
        }

        World overworld = Bukkit.getWorld("world");
        if (overworld == null) return;

        overworld.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        overworld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        overworld.setTime(18000);
        overworld.setThundering(true);
    }

    @EventHandler
    private void onRagnarokEndEvent(RagnarokEndEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ChatMessenger.sendCataclysmMessage(player, "El Ragnarök ha terminado.");

            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.25F, 0.84F);
            player.playSound(player, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1.25F, 0.84F);
        }

        World overworld = Bukkit.getWorld("world");
        if (overworld == null) return;

        overworld.setGameRule(GameRule.DO_WEATHER_CYCLE, true);
        overworld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        overworld.setTime(0);
        overworld.setThundering(false);
    }
}
