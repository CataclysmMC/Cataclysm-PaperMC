package symphony.utils;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ChatMessenger {

    public static void sendCataclysmMessage(Player player, String text) {
        player.sendMessage(MiniMessage.miniMessage().deserialize("<b><#666666>[</b><gradient:#C86060:#c87460>Cᴀᴛᴀᴄʟʏꜱᴍ</gradient><b><#666666>]</b> <#B9B9B9>» <#B9B9B9>" + text));
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.25F, 0.83F);
    }

    public static void sendStaffMessage(Player player, String text) {
        player.sendMessage(MiniMessage.miniMessage().deserialize("<b><#c6b2a2>[</b><#c89c60>Admin<b><#c6b2a2>]</b> <#cba78b>» <#999999>" + text));
        player.playSound(player, Sound.BLOCK_ANVIL_PLACE, 0.75F, 1.33F);
    }

}
