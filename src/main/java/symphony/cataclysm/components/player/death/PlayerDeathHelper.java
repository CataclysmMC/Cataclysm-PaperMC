package symphony.cataclysm.components.player.death;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeathHelper {
    private static @Getter @Setter List<BukkitTask> deathEventTasks = new ArrayList<>();

}
