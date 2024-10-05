package symphony.cataclysm.components.items;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum CataclysmItems {

    LANTEN_OF_THE_ABYSS(new ItemBuilder(Material.CARROT_ON_A_STICK).setDisplay("Linterna del Abismo")),

    ;

    private final ItemBuilder itemBuilder;

    CataclysmItems(ItemBuilder itemBuilder) {
        String id = name().toLowerCase();
        this.itemBuilder = itemBuilder.setID(id).setNBT(id);
    }
}
