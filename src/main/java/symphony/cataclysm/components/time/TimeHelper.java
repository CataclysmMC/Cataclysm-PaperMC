package symphony.cataclysm.components.time;

import lombok.Getter;
import lombok.Setter;
import symphony.cataclysm.Cataclysm;

import java.io.File;
import java.io.IOException;

public class TimeHelper {
    private static final @Getter String timeSystemConfigPath = "time-system.";
    private static final @Getter String currentDayConfigPath = TimeHelper.getTimeSystemConfigPath() + "current-day";
    private static final @Getter String currentWeekConfigPath = TimeHelper.getTimeSystemConfigPath() + "current-week";
    private static final @Getter String currentDayProgressConfigPath = TimeHelper.getTimeSystemConfigPath() + "day-progress";

    private static @Getter @Setter File timeManagementFile;

    public static void createTimeManagementFile() {
        File timeManagementFile = new File(Cataclysm.getInstance().getDataFolder(), "TimeManagement.yml");

        boolean doesTimeFileExist;
        try {
            doesTimeFileExist = timeManagementFile.createNewFile();

            if (!doesTimeFileExist) Cataclysm.logMessage("El archivo " + timeManagementFile.getName() + " ha sido creado exitosamente.");

            TimeHelper.setTimeManagementFile(timeManagementFile);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
