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

import moe.tristan.Lyrical.model.entity.Song;
import moe.tristan.Lyrical.model.integration.players.Player;
import moe.tristan.Lyrical.model.integration.players.PlayerSong;
import moe.tristan.Lyrical.model.lyricsproviders.LyricsServicesManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class Monitor<T extends Player> {
    private boolean shouldMonitor = false;

    private final T monitoredPlayer;
    private PlayerSong lastKnownSong = null;

    public Monitor(T playerToMonitor) {
        this.monitoredPlayer = playerToMonitor;
    }

    private void checkSong() {
        PlayerSong currentSong = monitoredPlayer
                .getCurrentlyPlayedSong(PlayerMonitorService.PLATFORM);

        if (!currentSong.equals(lastKnownSong)) {
            songChangedTo(currentSong);
        }
    }

    private void songChangedTo(PlayerSong newSong) {
        lastKnownSong = newSong;
        Song lyricizedSong = LyricsServicesManager.getInstance()
                .identifySong(
                        newSong.getTitle(),
                        newSong.getArtist()
                );

        PlayerMonitorService.getInstance().setCurrentSong(lyricizedSong);
    }

    public void beginMonitoring() {
        shouldMonitor = true;
        new Timer().scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (shouldMonitor) {
                            checkSong();
                        } else {
                            this.cancel();
                        }
                    }
                },
                0,
                1000
        );
    }
    public void stopMonitoring() {
        shouldMonitor = false;
    }
}
