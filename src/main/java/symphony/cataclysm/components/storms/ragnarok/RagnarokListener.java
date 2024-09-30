package symphony.cataclysm.components.storms.ragnarok;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import symphony.cataclysm.Cataclysm;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokEndEvent;
import symphony.cataclysm.components.storms.ragnarok.events.RagnarokStartEvent;
import symphony.utils.ChatMessenger;
import symphony.utils.NumberUtils;

public class RagnarokListener implements Listener {

    @EventHandler
    private void onRagnarokStartEvent(RagnarokStartEvent event) {
        int duration = event.getDuration();

        for (Player player : Bukkit.getOnlinePlayers()) {

            player.sendMessage("Duracion: " + NumberUtils.formatSeconds(duration, (duration >= 3600)));

            ChatMessenger.sendCataclysmMessage(player, "<gradient:#7999a6:#5f8abc>El Ragnarök nubla los cielos...</gradient>");

            player.playSound(player, Sound.BLOCK_END_PORTAL_SPAWN, 1.15F, 0.57F);
            player.playSound(player, Sound.ENTITY_SKELETON_HORSE_DEATH, 1.15F, 0.66F);

            Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> {
                player.playSound(player, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1.15F, 0.5F);
            }, 20);
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
        //TODO Implement logic...
    }

}
