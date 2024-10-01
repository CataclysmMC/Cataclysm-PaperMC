package symphony.cataclysm.components.player.death.animation;

public class DeathAnimationUtils {

    public static String convertUnicode(String unicodeInput) {
        if (unicodeInput.startsWith("\\u")) unicodeInput = unicodeInput.substring(2);

        int unicodeValue = Integer.parseInt(unicodeInput, 16);

        char[] charArray = Character.toChars(unicodeValue);

        return new String(charArray);
    }

}
