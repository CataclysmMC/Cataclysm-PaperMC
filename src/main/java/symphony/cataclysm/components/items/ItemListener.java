package symphony.cataclysm.components.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import symphony.cataclysm.Cataclysm;
import symphony.utils.PersistentData;

import java.util.List;

public class ItemListener implements Listener {

    @EventHandler
    private void onPlayerKillEntityEvent(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.getKiller() == null) return;

        Player player = entity.getKiller();
        PlayerInventory inventory = player.getInventory();

        ItemBuilder offHandBuilder = new ItemBuilder(inventory.getItemInOffHand());

        if (offHandBuilder.getID() != null && offHandBuilder.getID().equals("IMMUNITY_LANTERN")) {
            if (!(entity instanceof Monster) || player.hasCooldown(offHandBuilder.getItem().getType())) return;

            int storageLimit = 60 + (Cataclysm.getCurrentDay() - 1);

            String souls = offHandBuilder.getCustomData("IMMUNITY-LANTERN-SOUL");

            if (souls == null) souls = "0";

            if (Integer.parseInt(souls) >= storageLimit) return;

            souls = String.valueOf(Integer.parseInt(souls) + 1);
            offHandBuilder.setCustomData("IMMUNITY-LANTERN-SOUL", souls);

            List<Component> loreList = CataclysmItems.IMMUNITY_LANTERN.getItemBuilder().getMeta().lore();
            if (loreList != null) {
                loreList.add(Component.text(""));
                loreList.add(MiniMessage.miniMessage().deserialize("<#c2c2c2>☠ <#b092ff>Almas Almacenadas: <#9e9e9e>" + souls + "/" + storageLimit).decoration(TextDecoration.ITALIC, false));
            }

            offHandBuilder.getMeta().lore(loreList);
            offHandBuilder.getItem().setItemMeta(offHandBuilder.getMeta());

            if (Integer.parseInt(souls) != storageLimit) {
                player.playSound(player, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.75F, 0.77F);
                player.setCooldown(offHandBuilder.getItem().getType(), 20);
            } else {
                offHandBuilder.setGlint(true);
                player.playSound(player, Sound.BLOCK_BEACON_ACTIVATE, 0.75F, 0.77F);
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 0.75F, 0.77F);
            }

            inventory.setItemInOffHand(offHandBuilder.build());
        };
    }

}
