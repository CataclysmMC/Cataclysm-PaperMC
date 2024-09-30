package symphony.cataclysm.components.storms.ragnarok;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import symphony.cataclysm.Cataclysm;

public class RagnarokTask {
    private static @Getter @Setter int ragnarokProgresionTask;

    public static void runRagnarokProgresionTask() {
        Bukkit.getScheduler().cancelTask(getRagnarokProgresionTask());

        setRagnarokProgresionTask(Bukkit.getScheduler().scheduleSyncRepeatingTask(Cataclysm.getInstance(), () -> {
            if (RagnarokManager.getRagnarokCurrentProgress() == 0) {
                RagnarokManager.stopRagnarok();
            } else {
                RagnarokManager.setRagnarokCurrentProgress(RagnarokManager.getRagnarokCurrentProgress() - 1);
            }
        }, 0 ,20));
    }

}
