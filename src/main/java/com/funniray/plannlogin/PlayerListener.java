package com.funniray.plannlogin;

import com.djrapitops.plan.extension.Caller;
import com.nickuc.login.api.events.AsyncPremiumLoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener {

    private final PlannLogin plugin;
    private final Caller caller;

    public PlayerListener(PlannLogin plugin, Caller caller) {
        this.plugin = plugin;
        this.caller = caller;
    }

    @EventHandler
    public void onPremiumLogin(AsyncPremiumLoginEvent e) {
        plugin.setPremium(e.getPlayer().getUniqueId());
        caller.updatePlayerData(e.getPlayer().getUniqueId(), e.getPlayer().getName());
    }

}