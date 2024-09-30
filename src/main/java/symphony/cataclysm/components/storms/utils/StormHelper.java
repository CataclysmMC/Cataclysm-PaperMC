package symphony.cataclysm.components.storms.utils;

import lombok.Getter;
import lombok.Setter;
import symphony.cataclysm.Cataclysm;

import java.io.File;

public class StormHelper {
    private static @Getter @Setter File stormsFolder;

    public static void createStormsFolder() {
        StormHelper.setStormsFolder(new File(Cataclysm.getInstance().getDataFolder(), "Storms"));

        boolean isStormsFolderCreated = StormHelper.getStormsFolder().mkdir();

        if (!isStormsFolderCreated) Cataclysm.logMessage("El archivo " + StormHelper.getStormsFolder().getName() + " ha sido creado exitosamente.");
    }
}
