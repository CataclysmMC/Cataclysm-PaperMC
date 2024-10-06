package symphony.cataclysm.components.items;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum CataclysmItems {

    IMMUNITY_LANTERN(new ItemBuilder(Material.CARROT_ON_A_STICK)
            .setDisplay("<#7d3cff>Immunity Lantern")
            .setLore(
                    "<#c2c2c2>Este antiguo artefacto otorga",
                    "<#c2c2c2>inmunidad temporal, extraída de",
                    "<#c2c2c2>las almas atormentadas en su interior.",
                    "",
                    "<#b092ff>Al activarlo, serás inmune",
                    "<#b092ff>durante un segundo por cada alma",
                    "<#b092ff>almacenada en su interior.",
                    "<#b092ff>Puedes desactivarlo en cualquier",
                    "<#b092ff>momento para conservar el tiempo.",
                    "",
                    "<#9e9e9e>Para alimentarlo, asesina monstruos",
                    "<#9e9e9e>mientras la lámpara esté en tu",
                    "<#9e9e9e>segunda mano."
            )
    ),

    ;

    private final ItemBuilder itemBuilder;

    CataclysmItems(ItemBuilder itemBuilder) {
        this.itemBuilder = itemBuilder.setID(name()).setNBT(name());
    }
}
