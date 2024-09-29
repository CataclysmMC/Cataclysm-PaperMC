package symphony.cataclysm.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("cataclysm|cata")
public class CataclysmCommand extends BaseCommand {

    @Subcommand("day")
    private void day(CommandSender commandSender) {
        if (!(commandSender instanceof Player player)) return;
        player.sendMessage("Hola");
    }

}
