package symphony.cataclysm.utils;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ChatMessenger {

    public static void sendCataclysmMessage(Player player, String text) {
        player.sendMessage(MiniMessage.miniMessage().deserialize("<b><#666666>[</b><gradient:#C86060:#c87460>Cᴀᴛᴀᴄʟʏꜱᴍ</gradient><b><#666666>]</b> <#B9B9B9>» <#B9B9B9>" + text));
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.25F, 0.83F);
    }

    public static void sendStaffMessage(Player player, String text) {
        player.sendMessage(MiniMessage.miniMessage().deserialize("<b><#B9B9B9>[</b><#e7e7d5>Sᴇɴᴛɪɴᴇʟ<b><#B9B9B9>]</b> <#a3a3a3>» <#ddddd3>" + text));
        player.playSound(player, Sound.BLOCK_ANVIL_PLACE, 0.75F, 1.33F);
    }

}
