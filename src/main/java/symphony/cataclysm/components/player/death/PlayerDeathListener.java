package symphony.cataclysm.components.player.death;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import symphony.cataclysm.Cataclysm;
import symphony.cataclysm.components.player.death.animation.DeathAnimationManager;
import symphony.cataclysm.components.player.death.message.DeathMessageManager;
import symphony.cataclysm.components.storms.ragnarok.RagnarokManager;
import symphony.utils.ChatMessenger;

public class PlayerDeathListener implements Listener {

    @EventHandler
    private void onPlayerDeathEvent(PlayerDeathEvent event) {
        Component deathMessage = event.deathMessage();
        Player player = event.getPlayer();

        String playerName = player.getName();

        event.setCancelled(true);
        player.setStatistic(Statistic.DEATHS, player.getStatistic(Statistic.DEATHS) + 1);
        player.setGameMode(GameMode.SPECTATOR);

        for (Player players : Bukkit.getOnlinePlayers()) {
            ChatMessenger.sendCataclysmMessage(player, "<#ff4747>¡<#ff2c2c>" + playerName + " <#ff4747>ha sufrido la furia del CATACLISMO!");
            players.sendMessage(MiniMessage.miniMessage().deserialize("<#a2a1a1>" + playerName + ", " + new DeathMessageManager(player.getName()).getDeathMessage()));
            if (deathMessage != null) players.sendMessage(MiniMessage.miniMessage().deserialize("<gradient:#a74545:#562727>" + PlainTextComponentSerializer.plainText().serialize(deathMessage) + "</gradient>"));
        }

        DeathAnimationManager deathAnimationManager = new DeathAnimationManager(player);
        deathAnimationManager.playAnimation();
        deathAnimationManager.playSoundChain();

        Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 110, 0, true, false)), 15);

        Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> {
            if (!player.isOp()) player.banPlayer("¡No has sobrevivido al CATACLISMO!");
            RagnarokManager.startRangarok();
        }, 130);
    }
}
