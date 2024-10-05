package symphony.cataclysm.components.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import symphony.utils.PersistentData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ItemBuilder {
    private ItemMeta meta;
    private ItemStack item;

    public ItemBuilder(String base64) {this.item = this.createCustomHead(base64);}
    public ItemBuilder(ItemStack item) {this.item = item;}
    public ItemBuilder(Material material) {this.item = new ItemStack(material);}

    public ItemBuilder setDisplay(String display) {
        this.meta = this.item.getItemMeta();
        this.meta.displayName(MiniMessage.miniMessage().deserialize(display));
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setID(String value) {
        this.meta = this.item.getItemMeta();
        PersistentData.set(meta, "id", PersistentDataType.STRING, value);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public enum ChipColors {BLACK, BLUE, GREEN, RED, YELLOW}

    public ItemBuilder setChipColor(ChipColors color) {
        String value = color.name().toLowerCase();
        this.setCustomData("color", value);
        this.setNBT(value);
        return this;
    }

    public ItemBuilder setCustomData(String key, String value) {
        this.meta = this.item.getItemMeta();
        PersistentData.set(meta, key, PersistentDataType.STRING, value);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setLore(String text) {return this.setLore(text, "&7");}

    public ItemBuilder setLore(String text, String color) {
        this.meta = this.item.getItemMeta();
        final var lore = new ArrayList<Component>();
        if (this.meta.lore() != null) lore.addAll(this.meta.lore());
        for (var lines : this.splitLoreLines(text, color)) lore.add(MiniMessage.miniMessage().deserialize(lines));
        this.meta.lore(lore);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder addLore(String value) {
        final var lore = new ArrayList<Component>();
        this.meta = this.item.getItemMeta();
        if (this.meta.lore() != null) {
            lore.addAll(Objects.requireNonNull(this.meta.lore()));
            lore.add(MiniMessage.miniMessage().deserialize(value));
        }
        this.meta.lore(lore);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setNBT(String key) {
        final var stack = CraftItemStack.asNMSCopy(item);
        final var tag = stack.getOrCreateTag();
        tag.putInt(key, 1);
        stack.setTag(tag);
        this.item = stack.asBukkitCopy();
        return this;
    }

    public ItemBuilder setNBT(String key, int value) {
        final var stack = CraftItemStack.asNMSCopy(item);
        final var tag = stack.getOrCreateTag();
        tag.putInt(key, value);
        stack.setTag(tag);
        this.item = stack.asBukkitCopy();
        return this;
    }

    public ItemBuilder setOwner(String ownerName) {
        final var meta = (SkullMeta) this.item.getItemMeta();
        final var player = Bukkit.getOfflinePlayer(ownerName);
        meta.setOwningPlayer(player);
        PersistentData.set(meta, "owner", PersistentDataType.STRING, ownerName);
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int amplified) {
        this.meta = this.item.getItemMeta();
        this.meta.addEnchant(enchantment, amplified, true);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.meta = this.item.getItemMeta();
        this.meta.setUnbreakable(unbreakable);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setGlint(boolean glint) {
        this.meta = this.item.getItemMeta();
        if (glint) {
            this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
            this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        else {
            this.meta.removeEnchant(Enchantment.DURABILITY);
            this.meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setFlag(ItemFlag... flag) {
        this.meta = this.item.getItemMeta();
        this.meta.addItemFlags(flag);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setAttribute(Attribute attribute, AttributeModifier modifier) {
        this.meta = this.item.getItemMeta();
        this.meta.addAttributeModifier(attribute, modifier);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        this.meta = this.item.getItemMeta();
        if (!item.getType().name().contains("LEATHER")) return this;

        LeatherArmorMeta leatherMeta = (LeatherArmorMeta) this.meta;
        leatherMeta.setColor(color);
        this.item.setItemMeta(this.meta);
        return this;
    }

    public ItemBuilder setRocketPower(int power) {
        FireworkMeta fireworkMeta = (FireworkMeta) this.item.getItemMeta();

        if (meta != null) {
            fireworkMeta.setPower(power);
            this.item.setItemMeta(fireworkMeta);
        }
        return this;
    }

    public ItemStack build() {return this.item;}

    public String getID() {return PersistentData.get(this.item.getItemMeta(), "id", PersistentDataType.STRING);}

    public String getCustomData(String key) {return PersistentData.get(this.item.getItemMeta(), key, PersistentDataType.STRING);}

    public String getOwner() {return PersistentData.get(this.item.getItemMeta(), "owner", PersistentDataType.STRING);}

    @NotNull
    private ItemStack createCustomHead(String base64Texture) {
        final var playerHead = new ItemStack(Material.PLAYER_HEAD);
        final var skullMeta = (SkullMeta) playerHead.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64Texture));
        try {
            final var profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        }
        catch (NoSuchFieldException  | IllegalAccessException e) {e.fillInStackTrace();}

        playerHead.setItemMeta(skullMeta);
        return playerHead;
    }

    @NotNull
    private List<String> splitLoreLines(@NotNull String text, String color) {
        final var lore = new ArrayList<String>();
        final var builder = new StringBuilder();
        var line = "";
        for (var word : text.split("\\s+")) {
            if (builder.length() + word.length() + 1 > 28) {
                lore.add(line);
                builder.setLength(0);
            }
            builder.append(word).append(" ");
            line = color + "   " + builder.toString().trim();
        }
        if (!builder.isEmpty()) lore.add(line);
        return lore;
    }

}
