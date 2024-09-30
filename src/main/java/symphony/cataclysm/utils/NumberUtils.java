package symphony.cataclysm.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class NumberUtils {
    @NotNull
    @Contract(pure = true)
    public static String formatSeconds(int seconds, boolean displayHours) {
        var hours = seconds/3600;
        var minutes = (seconds/60) % 60;

        var formattedSeconds = seconds % 60;
        var formattedMinutes = String.format("%02d", minutes);

        if (displayHours) return hours + "ʜ " + formattedMinutes + "ᴍ " + formattedSeconds + "s";
        else return formattedMinutes + "ᴍ " + formattedSeconds + "s";
    }
}
