/*
 *     <DESCRIPTION>
 *     Copyright (C) 2016 Tristan Deloche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package moe.tristan.Lyrical.model.monitoring;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import moe.tristan.Lyrical.model.entity.Song;
import moe.tristan.Lyrical.model.integration.players.Player;
import moe.tristan.Lyrical.model.integration.system.SystemUtilities;
import moe.tristan.Lyrical.view.UIBridge;
import org.jetbrains.annotations.NotNull;

/**
 * Service class used to manage currently listened-to player.
 */
@Slf4j
public final class PlayerMonitorService {
    private static final PlayerMonitorService instance = new PlayerMonitorService();

    private Monitor<Player> trackedPlayer;

    @Getter
    private Song currentSong = Song.emptySong();

    private PlayerMonitorService() {
    }

    public static void setCurrentSong(@NotNull Song newSong) {
        log.info("The song changed to : " + newSong.getTitle() + " - " + newSong.getArtist());
        instance.currentSong = newSong;
        UIBridge.getInstance().songChanged(instance.currentSong);
    }

    public static void startMonitoringPlayer(@NotNull Class<? extends Player> playerClass) {
        try {
            boolean alreadyMonitoringPlayer = instance.trackedPlayer != null &&
                    instance.trackedPlayer.getCurrentClass().equals(playerClass);
            if (!alreadyMonitoringPlayer) {
                Player playerToTrack = playerClass.newInstance();
                if (playerToTrack.getSupportedOperatingSystems().contains(SystemUtilities.CURRENT_PLATFORM)) {
                    instance.trackedPlayer = new Monitor<>(playerToTrack);
                    instance.trackedPlayer.beginMonitoring();
                    log.info("Correctly started monitoring " + playerClass.getSimpleName());
                } else {
                    log.error(playerToTrack.getName() + " is not supported on " + SystemUtilities.CURRENT_PLATFORM);
                }
            } else {
                log.error("Already monitoring " + playerClass.getSimpleName() + ". Will switch now.");
                instance.trackedPlayer.stopMonitoring();
                startMonitoringPlayer(playerClass);
            }
        } catch (@NotNull IllegalAccessException | InstantiationException e) {
            log.error("An exception was thrown while trying to instantiate a " + playerClass.getName() + " player monitor", e);
        }
    }

    public static void stopMonitoringPlayer(@NotNull Class<? extends Player> playerClass) {
        if (instance.trackedPlayer.getMonitoredPlayer().equals(playerClass)) {
            instance.trackedPlayer.stopMonitoring();
        } else {
            log.error(
                    "No player of class " + playerClass + " is currently being "
                            + "monitored. Monitoring " +
                            instance.trackedPlayer.getMonitoredPlayer() +
                            " right now."
            );
        }
    }
}
