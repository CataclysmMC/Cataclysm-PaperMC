package symphony.cataclysm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import symphony.cataclysm.commands.CataclysmCommand;
import symphony.cataclysm.commands.AdminCommand;
import symphony.cataclysm.components.storms.ragnarok.RagnarokHelper;
import symphony.cataclysm.components.storms.ragnarok.RagnarokListener;
import symphony.cataclysm.components.storms.utils.StormHelper;
import symphony.cataclysm.components.time.TimeHelper;
import symphony.cataclysm.components.time.TimeManager;
import symphony.cataclysm.components.time.TimeTask;

public final class Cataclysm extends JavaPlugin {
    private static @Getter @Setter PaperCommandManager paperCommandManager;
    private static @Getter @Setter Cataclysm instance;

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

        Bukkit.getConsoleSender().sendMessage("El plugin se ha activado correctamente.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("El plugin se ha apagado correctamente.");
    }

    public static void logMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[Cataclysm] " + message);
    }
}
