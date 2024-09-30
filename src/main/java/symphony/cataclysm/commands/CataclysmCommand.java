package symphony.cataclysm.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import symphony.cataclysm.components.time.TimeManager;
import symphony.cataclysm.utils.ChatMessenger;
import symphony.cataclysm.utils.NumberUtils;

@CommandAlias("cataclysm|cata")
public class CataclysmCommand extends BaseCommand {

    @Subcommand("time")
    private void day(CommandSender commandSender) {
        if (!(commandSender instanceof Player player)) return;

        String hoursRemaining = NumberUtils.formatSeconds(86400 - TimeManager.getCurrentDayProgress(), true);
        ChatMessenger.sendCataclysmMessage(player, "Estamos en el día <#d35b5b>" + TimeManager.getCurrentDay() + " <#B9B9B9>de la semana <#d35b5b>" + TimeManager.getCurrentWeek() + "<#B9B9B9>, y faltan <#d35b5b>" + hoursRemaining + " <#B9B9B9>para que comience el siguiente día.");
    }

}
