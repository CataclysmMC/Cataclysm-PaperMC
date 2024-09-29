package symphony.cataclysm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import symphony.cataclysm.commands.CataclysmCommand;

public final class Cataclysm extends JavaPlugin {
    private static @Getter @Setter PaperCommandManager paperCommandManager;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("Configurando comandos...");
        Cataclysm.setPaperCommandManager(new PaperCommandManager(this));
        Cataclysm.getPaperCommandManager().registerCommand(new CataclysmCommand());

        Bukkit.getConsoleSender().sendMessage("El plugin se ha activado correctamente.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("El plugin se ha apagado correctamente.");
    }
}
