package symphony.cataclysm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import symphony.cataclysm.commands.CataclysmCommand;
import symphony.cataclysm.commands.AdminCommand;
import symphony.cataclysm.components.player.death.PlayerDeathListener;
import symphony.cataclysm.components.player.file.PlayerFileHelper;
import symphony.cataclysm.components.player.totem.PlayerTotemListener;
import symphony.cataclysm.components.storms.ragnarok.*;
import symphony.cataclysm.components.storms.utils.StormHelper;
import symphony.cataclysm.components.time.TimeHelper;
import symphony.cataclysm.components.time.TimeManager;
import symphony.cataclysm.components.time.TimeTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class Cataclysm extends JavaPlugin {
    public final @Getter ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private static @Getter @Setter PaperCommandManager paperCommandManager;
    private static @Getter @Setter Cataclysm instance;

    private static @Getter @Setter int currentDay;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("Creando instancia...");
        Cataclysm.setInstance(this);

        Bukkit.getConsoleSender().sendMessage("Configurando archivos...");
        if (!this.getDataFolder().mkdir()) Cataclysm.logMessage("El archivo principal ha sido creado exitosamente.");
        TimeHelper.createTimeManagementFile();

        Bukkit.getConsoleSender().sendMessage("Configurando comandos...");
        Cataclysm.setPaperCommandManager(new PaperCommandManager(this));
        Cataclysm.getPaperCommandManager().registerCommand(new CataclysmCommand());
        Cataclysm.getPaperCommandManager().registerCommand(new AdminCommand());

        Bukkit.getConsoleSender().sendMessage("Configurando eventos...");
        StormHelper.createStormsFolder();
        RagnarokHelper.createRagnarokEventFile();

        Bukkit.getConsoleSender().sendMessage("Iniciando tareas asíncronas...");
        TimeTask.runDayProgresionTask();

        Bukkit.getConsoleSender().sendMessage("Insertando valores por defecto...");
        if (TimeManager.getCurrentWeek() == 0) TimeManager.setCurrentWeek(1);

        Bukkit.getConsoleSender().sendMessage("Registrando eventos...");
        Bukkit.getPluginManager().registerEvents(new RagnarokListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerTotemListener(), this);

        Bukkit.getConsoleSender().sendMessage("Configurando tormentas...");
        if (RagnarokManager.isRagnarokActivated()) {
            RagnarokBossbar.setUpRagnarokBossbar();
            RagnarokTask.runRagnarokProgresionTask();
            for (Player player : Bukkit.getOnlinePlayers()) RagnarokBossbar.displayRagnarokBossbar(player);
        }

        Bukkit.getConsoleSender().sendMessage("Configurando jugadores...");
        PlayerFileHelper.createPlayersFolder();

        Bukkit.getConsoleSender().sendMessage("Configurando plugin internamente...");
        Cataclysm.setCurrentDay(TimeManager.getCurrentDay());

        Bukkit.getConsoleSender().sendMessage("El plugin se ha activado correctamente.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Desactivando tormentas...");
        if (RagnarokManager.isRagnarokActivated()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                RagnarokBossbar.removeRagnarokBossbar(player);
            }
        }

        Bukkit.getConsoleSender().sendMessage("El plugin se ha apagado correctamente.");
    }

    public static void logMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[Cataclysm] " + message);
    }
}
