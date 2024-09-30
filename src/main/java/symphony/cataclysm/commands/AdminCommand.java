package symphony.cataclysm.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import symphony.cataclysm.components.storms.ragnarok.RagnarokManager;
import symphony.cataclysm.components.time.TimeManager;
import symphony.utils.ChatMessenger;
import symphony.utils.NumberUtils;

@CommandAlias("catadmin")
public class AdminCommand extends BaseCommand {

    @Subcommand("ragnarok start")
    private void ragnarokStart() {
        RagnarokManager.startRangarok();
    }

    @Subcommand("ragnarok stop")
    private void ragnarokStop() {
        RagnarokManager.stopRagnarok();
    }

    @Subcommand("ragnarok setprogress")
    private void ragnarokSetProgress(CommandSender commandSender, int progress) {
        if (!RagnarokManager.isRagnarokActivated()) {
            if ((commandSender instanceof Player player)) ChatMessenger.sendStaffMessage(player, "No hay un Ragnarök activo en este momento.");
            return;
        }

        RagnarokManager.setRagnarokCurrentProgress(progress);

        if ((commandSender instanceof Player player)) {
            ChatMessenger.sendStaffMessage(player, "Has modificado el progreso del Ragnarök ha: " + NumberUtils.formatSeconds(progress, true));
        }
    }

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
