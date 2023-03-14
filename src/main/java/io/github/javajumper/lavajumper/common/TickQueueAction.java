package io.github.javajumper.lavajumper.common;

import net.minecraft.client.MinecraftClient;

public interface TickQueueAction {
    public boolean isStillValid(MinecraftClient client);

    /**
     * Action to execute
     * @param client
     * @return if true this actions will be counted as successful and will be last executed action this tick
     */
    public boolean executeAction(MinecraftClient client);
}
