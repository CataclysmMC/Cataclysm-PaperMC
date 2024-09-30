package symphony.cataclysm.components.time;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import symphony.cataclysm.Cataclysm;

public class TimeTask {

    public static void runDayProgresionTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Cataclysm.getInstance(), () -> {
            int currentDayProgress = TimeManager.getCurrentDayProgress();

            if (currentDayProgress == 86400) {
                TimeManager.setCurrentDay(TimeManager.getCurrentDay() + 1);

                if (TimeManager.getCurrentDay() % 7 == 0) {
                    TimeManager.setCurrentWeek(TimeManager.getCurrentWeek() + 1);
                }
            }
            else {
                TimeManager.setCurrentDayProgress(currentDayProgress + 1);
            }
        }, 0, 20);
    }

}
