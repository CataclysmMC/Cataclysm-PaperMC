package symphony.cataclysm.components.player.death.message;

import lombok.Getter;
import symphony.cataclysm.components.player.file.PlayerFileHelper;

public class DeathMessageHelper {

    private static final @Getter String deathMessageConfigPath = PlayerFileHelper.getInformationConfigPath() + "death-message";

}
