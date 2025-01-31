package com.willfp.stattrackers.stats.stats

import com.willfp.eco.core.events.EntityDeathByEntityEvent
import com.willfp.stattrackers.stats.Stat
import com.willfp.stattrackers.stats.incrementIfToTrack
import com.willfp.stattrackers.stats.tryAsPlayer
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority

class StatMobsKilled : Stat("mobs_killed") {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun statListener(event: EntityDeathByEntityEvent) {
        val player = event.killer.tryAsPlayer() ?: return

        val itemStack = player.inventory.itemInMainHand

        if (itemStack.type == Material.AIR) {
            return
        }

        if (itemStack.type.maxStackSize > 1) {
            return
        }

        itemStack.incrementIfToTrack(this, 1.0)
    }
}
