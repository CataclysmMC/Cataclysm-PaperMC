package symphony.cataclysm.components.player.death;

import lombok.Getter;
import symphony.cataclysm.components.player.file.PlayerFileHelper;

public class PlayerDeathHelper {

    private static final @Getter String deathMessageConfigPath = PlayerFileHelper.getInformationConfigPath() + "death-message";

}
