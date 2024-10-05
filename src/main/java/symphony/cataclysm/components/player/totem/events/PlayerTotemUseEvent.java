package symphony.cataclysm.components.player.totem.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerTotemUseEvent extends Event {

    private final Player player;
    private final int poppedTotems;
    private final EntityDamageEvent damageEvent;

    public PlayerTotemUseEvent(Player player, int poppedTotems, EntityDamageEvent damageEvent) {
        this.player = player;
        this.poppedTotems = poppedTotems;
        this.damageEvent = damageEvent;
    }

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {return handlers;}

    @NotNull
    public static HandlerList getHandlerList() {return handlers;}

}