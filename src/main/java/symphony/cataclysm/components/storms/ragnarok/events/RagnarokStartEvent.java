package symphony.cataclysm.components.storms.ragnarok.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class RagnarokStartEvent extends Event {
    private final int duration;

    public RagnarokStartEvent(int duration) {
        this.duration = duration;
    }

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {return handlers;}

    @NotNull
    public static HandlerList getHandlerList() {return handlers;}

}
