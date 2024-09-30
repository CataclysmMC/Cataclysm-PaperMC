package symphony.cataclysm.components.storms.ragnarok;

import lombok.Getter;
import lombok.Setter;
import symphony.cataclysm.Cataclysm;
import symphony.cataclysm.components.storms.utils.StormHelper;

import java.io.File;
import java.io.IOException;

public class RagnarokHelper {
    private static final @Getter String ragnarokEventConfigPath = "ragnarok-event.";
    private static final @Getter String ragnarokLevelConfigPath = RagnarokHelper.getRagnarokEventConfigPath() + "current-level";
    private static final @Getter String ragnarokCurrentProgress = RagnarokHelper.getRagnarokEventConfigPath() + "current-progress";

    private static @Getter @Setter File ragnarokEventFile;

    public static void createRagnarokEventFile() {
        RagnarokHelper.setRagnarokEventFile(new File(StormHelper.getStormsFolder(), "RagnarokEvent.yml"));

        boolean doesRagnarokEventFileExists;
        try {
            doesRagnarokEventFileExists = RagnarokHelper.getRagnarokEventFile().createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!doesRagnarokEventFileExists) Cataclysm.logMessage("El archivo RagnarokEvent ha sido creado exitosamente.");
    }

}
