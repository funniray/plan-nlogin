package com.funniray.plannlogin;

import com.djrapitops.plan.extension.CallEvents;
import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.annotation.BooleanProvider;
import com.djrapitops.plan.extension.annotation.GroupProvider;
import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

import java.util.UUID;

@PluginInfo(name = "nLogin", iconName = "key", iconFamily = Family.SOLID, color = Color.NONE)
public class LoginExtension implements DataExtension {

    private final PlannLogin plugin;

    @Override
    public CallEvents[] callExtensionMethodsOn() {
        return new CallEvents[]{
                CallEvents.PLAYER_JOIN,
                CallEvents.PLAYER_LEAVE
        };
    }

    public LoginExtension(PlannLogin plugin) {
        this.plugin = plugin;
    }

    @BooleanProvider(text = "Premium Account", priority = 100, conditionName = "isPremium", iconName = "star")
    public boolean isPremiumBoolean(UUID uuid) {
        return plugin.isPremium(uuid);
    }

    @GroupProvider(text = "PremiumGroup", iconName = "star")
    public String[] isPremiumGroup(UUID uuid) {
        return new String[]{plugin.isPremium(uuid)?"Premium":"Offline"};
    }

}
