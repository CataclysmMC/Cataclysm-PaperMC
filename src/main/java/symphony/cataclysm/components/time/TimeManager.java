package symphony.cataclysm.components.time;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import symphony.cataclysm.Cataclysm;

import java.io.IOException;

@Getter
public class TimeManager {

    public static void setCurrentWeek(int week) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile());

        config.set(TimeHelper.getCurrentWeekConfigPath(), week);

        try {
            config.save(TimeHelper.getTimeManagementFile());
            Cataclysm.logMessage("La semana ha sido modificada a la número " + week);
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + TimeHelper.getTimeManagementFile().getName(), exception);
        }
    }

    public static void setCurrentDay(int day) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile());

        config.set(TimeHelper.getCurrentDayConfigPath(), day);

        try {
            config.save(TimeHelper.getTimeManagementFile());
            TimeManager.setCurrentDayProgress(0);
            Cataclysm.logMessage("El día ha sido modificado al número " + day);
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + TimeHelper.getTimeManagementFile().getName(), exception);
        }
    }

    public static void setCurrentDayProgress(int progress) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile());

        config.set(TimeHelper.getCurrentDayProgressConfigPath(), progress);

        try {
            config.save(TimeHelper.getTimeManagementFile());
        } catch (IOException exception) {
            throw new RuntimeException("Hubo un error al intentar guardar el archivo: " + TimeHelper.getTimeManagementFile().getName(), exception);
        }
    }

    public static int getCurrentWeek() {
        return YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile()).getInt(TimeHelper.getCurrentWeekConfigPath());
    }

    public static int getCurrentDayProgress() {
        return YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile()).getInt(TimeHelper.getCurrentDayProgressConfigPath());
    }

    public static int getCurrentDay() {
        return YamlConfiguration.loadConfiguration(TimeHelper.getTimeManagementFile()).getInt(TimeHelper.getCurrentDayConfigPath());
    }
}
