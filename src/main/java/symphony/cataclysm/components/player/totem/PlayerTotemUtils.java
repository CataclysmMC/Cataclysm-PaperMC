package symphony.cataclysm.components.player.totem;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerTotemUtils {

    public static String formatDamageCause(EntityDamageEvent event) {
        switch (event.getCause()) {
            case FALL -> {
                return "Caída";
            }
            case FIRE -> {
                return "Fuego";
            }
            case LAVA -> {
                return "Lava";
            }
            case VOID -> {
                return "Vacio";
            }
            case MAGIC -> {
                return "Magia";
            }
            case CUSTOM -> {
                return "Ataque Cardiaco";
            }
            case FREEZE -> {
                return "Congelación";
            }
            case POISON -> {
                return "Veneno";
            }
            case THORNS -> {
                return "Espinas";
            }
            case WITHER -> {
                return "Descomposición";
            }
            case CONTACT -> {
                return "Contacto";
            }
            case MELTING -> {
                return "Derretimiento";
            }
            case SUICIDE -> {
                return "Suicidio";
            }
            case CRAMMING, SUFFOCATION -> {
                return "Sofocación";
            }
            case FIRE_TICK -> {
                return "Ticks de Fuego";
            }
            case HOT_FLOOR -> {
                return "Bloque de Magma";
            }
            case LIGHTNING -> {
                return "Rayo";
            }
            case STARVATION -> {
                return "Hambre";
            }
            case SONIC_BOOM -> {
                return "Onda Sónica";
            }
            case DRAGON_BREATH -> {
                return "Aliento de Dragón";
            }
            case FALLING_BLOCK -> {
                return "Idiota";
            }
            case FLY_INTO_WALL -> {
                return "Energía Cinética";
            }
            case ENTITY_SWEEP_ATTACK -> {
                return "Barrido de Ataque";
            }
            case DRYOUT, DROWNING -> {
                return "Ahogamiento";
            }
            case ENTITY_ATTACK -> {
                if (event instanceof EntityDamageByEntityEvent entityDamageByEntityEvent)
                    return "Ataque de " + entityDamageByEntityEvent.getDamager().getName();
                else return "Ataque de entidad";
            }
            case PROJECTILE -> {
                if (event instanceof Arrow arrow) return "Proyectil de " + arrow.getShooter();
                else if (event instanceof Snowball snowball) return "Proyectil de " + snowball.getShooter();
                else return "Proyectil";
            }
            case ENTITY_EXPLOSION, BLOCK_EXPLOSION -> {
                if (event instanceof EntityDamageByEntityEvent entityDamageByEntityEvent)
                    return "Explosión de " + entityDamageByEntityEvent.getDamager().getName();
                else return "Explosión";
            }
            default -> {
                return "Desconocido";
            }
        }
    }

}
