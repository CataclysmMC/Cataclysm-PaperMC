package symphony.cataclysm.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import symphony.cataclysm.components.time.TimeManager;
import symphony.cataclysm.utils.ChatMessenger;
import symphony.cataclysm.utils.NumberUtils;

@CommandAlias("catadmin")
public class AdminCommand extends BaseCommand {

    @Subcommand("day setcurrent")
    private void daySetCurrent(CommandSender commandSender, int newDay) {
        TimeManager.setCurrentDay(newDay);

        int week = newDay/7;
        TimeManager.setCurrentWeek(week + 1);

        if (commandSender instanceof Player player) {
            ChatMessenger.sendStaffMessage(player, "Has modificado el día al numero: " + newDay);
        }
    }

    @Subcommand("day setprogress")
    private void daySetProgress(CommandSender commandSender, int seconds) {
        TimeManager.setCurrentDayProgress(seconds);

        if (commandSender instanceof Player player) {
            ChatMessenger.sendStaffMessage(player, "Has modificado el progreso del dia " + TimeManager.getCurrentDay() + " a: " + NumberUtils.formatSeconds(seconds, true));
        }
    }

}
