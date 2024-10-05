package symphony.cataclysm.components.player.totem;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.PlayerInventory;
import symphony.cataclysm.components.player.totem.events.PlayerTotemUseEvent;
import symphony.utils.ChatMessenger;

public class PlayerTotemListener implements Listener {

    @EventHandler
    private void playerTotemUseEvent(PlayerTotemUseEvent event) {
        Player player = event.getPlayer();

        PlayerTotemManager totemManager = new PlayerTotemManager(player);

        int totemsPopped = totemManager.getPoppedTotems() + 1;

        totemManager.setPoppedTotems(totemsPopped);

        for (Player players : Bukkit.getOnlinePlayers()) {
            ChatMessenger.sendCataclysmMessage(players, "<#939393>¡<#e56969>" + player.getName() + "<#939393> ha usado su tótem <#696969>#<#e56969>" + totemsPopped + "<#939393>!");
            players.sendMessage(MiniMessage.miniMessage().deserialize("<#696969>(Razón: " + PlayerTotemUtils.formatDamageCause(event.getDamageEvent()) + ")"));
            players.playSound(players, Sound.ENTITY_GUARDIAN_DEATH, 1.25F, 0.55F);
        }
    }

    @EventHandler
    private void entityResurrectEvent(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        PlayerInventory inventory = player.getInventory();

        if (inventory.getItemInMainHand().getType().equals(Material.TOTEM_OF_UNDYING) || inventory.getItemInOffHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
            new PlayerTotemUseEvent(player, new PlayerTotemManager(player).getPoppedTotems(), player.getLastDamageCause()).callEvent();
        }
    }

}
