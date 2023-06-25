package com.funniray.plannlogin;

import com.djrapitops.plan.capability.CapabilityService;
import com.djrapitops.plan.extension.Caller;
import com.djrapitops.plan.extension.ExtensionService;

import java.util.Optional;

public class nLoginPlanHook {
    public void hookIntoPlan(PlannLogin plugin) {
        if (!areAllCapabilitiesAvailable()) return;
        registerDataExtension(plugin);
        listenForPlanReloads(plugin);
    }

    private boolean areAllCapabilitiesAvailable() {
        CapabilityService capabilities = CapabilityService.getInstance();
        return capabilities.hasCapability("DATA_EXTENSION_VALUES");
    }

    private void registerDataExtension(PlannLogin plugin) {
        try {

            Optional<Caller> callerOptional = ExtensionService.getInstance().register(new LoginExtension(plugin));

            callerOptional.ifPresent(caller ->
                    plugin.getServer().getPluginManager().registerEvents(new PlayerListener(plugin, caller), plugin)
            );

        } catch (IllegalStateException planIsNotEnabled) {
            // Plan is not enabled, handle exception
        } catch (IllegalArgumentException dataExtensionImplementationIsInvalid) {
            // The DataExtension implementation has an implementation error, handle exception
        }
    }

    private void listenForPlanReloads(PlannLogin plugin) {
        CapabilityService.getInstance().registerEnableListener(
                isPlanEnabled -> {
                    // Register DataExtension again
                    if (isPlanEnabled) registerDataExtension(plugin);
                }
        );
    }
}
