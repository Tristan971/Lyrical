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

package moe.tristan.Lyrical.model.integration.players.playersimpl;

import com.jacob.com.ComThread;
import com.jacob.com.DispatchEvents;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import moe.tristan.Lyrical.model.integration.players.Player;
import moe.tristan.Lyrical.model.integration.players.PlayerSong;
import moe.tristan.Lyrical.model.integration.system.OperatingSystem;
import moe.tristan.Lyrical.model.integration.system.SystemUtilities;
import moe.tristan.Lyrical.model.integration.system.Windows.WindowsNT;
import moe.tristan.Lyrical.model.integration.system.macOS.macOS;
import moe.tristan.Lyrical.model.monitoring.PlayerMonitorService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class managing the iTunes player
 */
@SuppressWarnings("WeakerAccess")
@Slf4j
@Data
public final class iTunes implements Player {
    private boolean isMonitoring = false;
    private final String name = "iTunes";
    private final Set<OperatingSystem> supportedOperatingSystems =
            new HashSet<>(
                    Arrays.asList(
                            macOS.INSTANCE,
                            WindowsNT.INSTANCE
                    )
            );

    @Override
    public PlayerSong getCurrentlyPlayedSong() {
        if (SystemUtilities.CURRENT_PLATFORM instanceof macOS) {
            return getSong_macOS();
        } else if (SystemUtilities.CURRENT_PLATFORM instanceof WindowsNT) {
            return getSong_WindowsNT();
        } else {
            return getSong_Unsupported();
        }
    }

    @NotNull
    public static PlayerSong getSong_macOS() {
        final String scriptForName =
                "on run\n"
                        + "  set info to \"\"\n"
                        + "  tell application \"System Events\"\n"
                        + "    set num to count (every process whose name is \"iTunes\")\n"
                        + "  end tell\n"
                        + "  if num > 0 then\n"
                        + "    tell application \"iTunes\"\n"
                        + "      set trackname to name of current track\n"
                        + "    end tell\n"
                        + "  end if\n"
                        + "  return trackname\n"
                        + "end run";

        String name = macOS.INSTANCE.runAppleScriptNew(scriptForName);

        final String scriptForArtist =
                "on run\n"
                        + "  set info to \"\"\n"
                        + "  tell application \"System Events\"\n"
                        + "    set num to count (every process whose name is \"iTunes\")\n"
                        + "  end tell\n"
                        + "  if num > 0 then\n"
                        + "    tell application \"iTunes\"\n"
                        + "      set artistname to artist of current track\n"
                        + "    end tell\n"
                        + "  end if\n"
                        + "  return artistname\n"
                        + "end run";

        String artist = macOS.INSTANCE.runAppleScriptNew(scriptForArtist);

        return PlayerSong.builder()
                .title(name)
                .artist(artist)
                .build();
    }

    @Contract(pure = true)
    @NotNull
    public static PlayerSong getSong_WindowsNT() {
        return iTunesCOMEvents.lastComEventSong;
    }

    @NotNull
    public static PlayerSong getSong_Unsupported() {
        log.error("iTunes monitoring is not supported on this platform. Stopping monitoring.");
        PlayerMonitorService.stopMonitoringPlayer(iTunes.class);
        return PlayerSong.builder()
                .title("Unknown Song")
                .artist("Unknown artist")
                .build();
    }

    @Override
    public void startMonitoring() {
        if (SystemUtilities.CURRENT_PLATFORM instanceof WindowsNT) {
            ComThread.InitMTA(true);
            DispatchEvents events = WindowsNT.initJacobEvents(
                    "iTunes.Application",
                    iTunesCOMEvents.instance
            );
        }

        isMonitoring = true;
    }

    @Override
    public void stopMonitoring() {
        if (SystemUtilities.CURRENT_PLATFORM instanceof WindowsNT) {
            ComThread.Release();
        }

        isMonitoring = false;
    }

    @Override
    public boolean isMonitoring() {
        return isMonitoring;
    }
}


