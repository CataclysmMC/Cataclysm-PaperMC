package symphony.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import symphony.cataclysm.Cataclysm;

import java.util.Objects;

public class PersistentData {

    @Nullable
    public static <T, Z> Z get(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type) {
        if (!PersistentData.has(holder, key, type)) return null;
        return PersistentData.getDataContainer(holder).get(PersistentData.key(key), type);
    }

    public static <T, Z> boolean has(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type) {
        if (holder == null || holder.getPersistentDataContainer().get(PersistentData.key(key), type) == null) return false;
        return PersistentData.getDataContainer(holder).has(PersistentData.key(key), type);
    }

    public static <T, Z> boolean equals(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type, Z value) {
        if (!PersistentData.has(holder, key, type)) return false;
        return Objects.equals(PersistentData.get(holder, key, type), value) || value.equals(PersistentData.get(holder, key, type));
    }

    public static <T, Z> void set(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type, Z value) {
        PersistentData.getDataContainer(holder).set(PersistentData.key(key), type, value);
    }

    @NotNull
    @Contract("_ -> new")
    public static NamespacedKey key(String string) {
        return new NamespacedKey(Cataclysm.getInstance(), string);
    }

    @NotNull
    private static PersistentDataContainer getDataContainer(@NotNull PersistentDataHolder holder) {
        return holder.getPersistentDataContainer();
    }

}
