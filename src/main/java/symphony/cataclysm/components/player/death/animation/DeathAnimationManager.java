package symphony.cataclysm.components.player.death.animation;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import symphony.cataclysm.Cataclysm;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Getter
public class DeathAnimationManager {
    private final Player deathPlayer;
    private @Getter @Setter ScheduledFuture<?> scheduledFuture;

    public DeathAnimationManager(Player deathPlayer) {
        this.deathPlayer = deathPlayer;
    }

    public void playAnimation() {
        List<String> frames = new ArrayList<>();

        for (int i = 0; i < 65; i++) {
            var format = "000";
            if (i < 10) format = "00" + i;
            if (i >= 10) format = "0" + i;
            frames.add("\\" + "uE" + format);
        }

        int[] tick = {0};
        this.setScheduledFuture(Cataclysm.getInstance().getService().scheduleWithFixedDelay(() -> {
            tick[0]++;

            for (Player players : Bukkit.getOnlinePlayers()) {
                players.showTitle(Title.title(
                        MiniMessage.miniMessage().deserialize("#4e5c24" + DeathAnimationUtils.convertUnicode(frames.get(tick[0]))),
                        Component.text(""),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(250), Duration.ofSeconds(4))
                ));
            }

            if (tick[0] > 64) {
                this.getScheduledFuture().cancel(true);
                this.setScheduledFuture(null);
            }
        }, 0, 70, TimeUnit.MILLISECONDS));
    }

    public void playSoundChain() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.85F, 0.95F);
            players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1.25F, 0.55F);

            Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> {
                players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.85F, 1.15F);
                players.playSound(players, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.85F, 0.5F);
            }, 15);

            Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> {
                players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.85F, 1.3F);
                players.playSound(players, Sound.ENTITY_GUARDIAN_DEATH, 1.25F, 0.5F);
            }, 45);

            Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> {
                players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.85F, 1.05F);
                players.playSound(players, Sound.ITEM_TRIDENT_THUNDER, 1.25F, 1.35F);
            }, 55);

            Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> {
                players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.85F, 1.33F);
                players.playSound(players, Sound.ENTITY_SKELETON_HORSE_DEATH, 1.35F, 0.85F);
            }, 65);

            Bukkit.getScheduler().runTaskLater(Cataclysm.getInstance(), () -> {
                players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.85F, 1.5F);
                players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1.35F, 0.85F);
                players.playSound(players, Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 1.45F, 0.55F);
            }, 75);
        }
    }
}
