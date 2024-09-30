package symphony.cataclysm.components.storms.ragnarok.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class RagnarokEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {return handlers;}

    @NotNull
    public static HandlerList getHandlerList() {return handlers;}

}
