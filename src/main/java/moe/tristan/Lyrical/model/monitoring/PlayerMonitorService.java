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
import moe.tristan.Lyrical.view.UIBridge;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public final class PlayerMonitorService {
    private static final PlayerMonitorService instance = new PlayerMonitorService();

    private Monitor<Player> trackedPlayer;

    @Getter
    private Song currentSong = Song.emptySong();

    public static void setCurrentSong(@NotNull Song newSong) {
        System.out.println("The song changed to : "+newSong.getTitle()+" - "+newSong.getArtist());
        instance.currentSong = newSong;
        UIBridge.getInstance().songChanged(instance.currentSong);
    }

    private PlayerMonitorService() {}

    public static void startMonitoringPlayer(@NotNull Class<? extends Player> playerClass) {
        try {
            boolean alreadyMonitoringPlayer = instance.trackedPlayer != null && instance.trackedPlayer.getClass().equals(playerClass);
            if (!alreadyMonitoringPlayer) {
                Player playerToTrack = playerClass.newInstance();
                instance.trackedPlayer = new Monitor<>(playerToTrack);
                instance.trackedPlayer.beginMonitoring();
                System.out.println("Correctly started monitoring "+playerClass.getSimpleName());
            } else {
                System.err.println("Already monitoring "+playerClass.getSimpleName()+". Will switch now.");
                instance.trackedPlayer.stopMonitoring();
                startMonitoringPlayer(playerClass);
            }
        } catch (@NotNull IllegalAccessException | InstantiationException e) {
            System.err.println("An " + e.getClass() + " was thrown while trying to instantiate a " + playerClass.getName() + " player monitor");
            System.err.println(e.getMessage());
        }
    }
}
