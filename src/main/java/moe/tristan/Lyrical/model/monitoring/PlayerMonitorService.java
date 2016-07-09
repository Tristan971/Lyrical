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
import moe.tristan.Lyrical.model.entity.Song;
import moe.tristan.Lyrical.model.integration.players.Player;
import moe.tristan.Lyrical.model.integration.system.OperatingSystem;
import moe.tristan.Lyrical.model.integration.system.SystemUtilities;
import moe.tristan.Lyrical.view.UIBridge;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public final class PlayerMonitorService {
    private static final PlayerMonitorService INSTANCE = new PlayerMonitorService();
    public static PlayerMonitorService getInstance() {
        return INSTANCE;
    }

    public static final OperatingSystem PLATFORM = SystemUtilities.getCurrentOperatingSystem();

    private Monitor<Player> trackedPlayer;

    @Getter
    private Song currentSong = Song.emptySong();

    public void setCurrentSong(Song newSong) {
        this.currentSong = newSong;
        UIBridge.getInstance().songChanged(currentSong);
    }

    private PlayerMonitorService() {}

    public void startMonitoringPlayer(Class<? extends Player> playerClass) {
        try {
            boolean alreadyMonitoringPlayer = trackedPlayer != null && trackedPlayer.getClass().equals(playerClass);
            if (!alreadyMonitoringPlayer) {
                Player playerToTrack = playerClass.newInstance();
                trackedPlayer = new Monitor<>(playerToTrack);
                trackedPlayer.beginMonitoring();
                System.out.println("Correctly started monitoring "+playerClass.getSimpleName());
            } else {
                System.err.println("Already monitoring "+playerClass.getSimpleName()+". Will switch now.");
                trackedPlayer.stopMonitoring();
                startMonitoringPlayer(playerClass);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            System.err.println("An " + e.getClass() + " was thrown while trying to instantiate a " + playerClass.getName() + " player monitor");
            System.err.println(e.getMessage());
        }
    }
}
