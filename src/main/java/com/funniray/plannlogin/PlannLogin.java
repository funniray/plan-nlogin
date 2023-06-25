package com.funniray.plannlogin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlannLogin extends JavaPlugin {

    private final Map<UUID,Boolean> premium = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            new nLoginPlanHook().hookIntoPlan(this);
        } catch (NoClassDefFoundError e) {
            getLogger().info("Plan or nLogin is not installed and cannot be hooked into.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean isPremium(UUID uuid) {
        return premium.containsKey(uuid) && premium.get(uuid);
    }

    public void setPremium(UUID uuid, boolean isPrem) {
        premium.put(uuid, isPrem);
    }

    public void setPremium(UUID uuid) {
        setPremium(uuid, true);
    }
}
