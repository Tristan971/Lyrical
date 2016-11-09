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
import moe.tristan.Lyrical.model.integration.players.Player;
import moe.tristan.Lyrical.model.integration.players.PlayerSong;
import moe.tristan.Lyrical.model.integration.system.OperatingSystem;
import moe.tristan.Lyrical.model.integration.system.SystemUtilities;
import moe.tristan.Lyrical.model.integration.system.Windows.WindowsNT;
import moe.tristan.Lyrical.model.integration.system.macOS.macOS;
import moe.tristan.Lyrical.model.monitoring.PlayerMonitorService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class managing the iTunes player
 */
@SuppressWarnings("WeakerAccess")
public final class iTunes implements Player {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(iTunes.class);
    private final String name = "iTunes";
    private final Set<OperatingSystem> supportedOperatingSystems =
            new HashSet<>(
                    Arrays.asList(
                            macOS.getInstance(),
                            WindowsNT.getInstance()
                    )
            );
    private boolean isMonitoring = false;

    public iTunes() {
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

        String name = macOS.getInstance().runAppleScriptNew(scriptForName);

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

        String artist = macOS.getInstance().runAppleScriptNew(scriptForArtist);

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
    public PlayerSong getCurrentlyPlayedSong() {
        if (SystemUtilities.CURRENT_PLATFORM instanceof macOS) {
            return getSong_macOS();
        } else if (SystemUtilities.CURRENT_PLATFORM instanceof WindowsNT) {
            return getSong_WindowsNT();
        } else {
            return getSong_Unsupported();
        }
    }

    @Override
    public void startMonitoring() {
        if (SystemUtilities.CURRENT_PLATFORM instanceof WindowsNT) {
            ComThread.InitMTA(true);
            WindowsNT.initJacobEvents(
                    "iTunes.Application",
                    iTunesCOMEvents.instance
            );
        }

        isMonitoring = true;
    }

    @Override
    public void stopMonitoring() {
        if (SystemUtilities.CURRENT_PLATFORM instanceof WindowsNT) {
            log.info("Stopping the iTunes COM connection thread and releasing all objects.");
            ComThread.Release();
        }
        isMonitoring = false;
    }

    @Override
    public boolean isMonitoring() {
        return isMonitoring;
    }

    public void setMonitoring(boolean isMonitoring) {
        this.isMonitoring = isMonitoring;
    }

    public String getName() {
        return this.name;
    }

    public Set<OperatingSystem> getSupportedOperatingSystems() {
        return this.supportedOperatingSystems;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof iTunes)) return false;
        final iTunes other = (iTunes) o;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$supportedOperatingSystems = this.getSupportedOperatingSystems();
        final Object other$supportedOperatingSystems = other.getSupportedOperatingSystems();
        return (this$supportedOperatingSystems == null ?
                other$supportedOperatingSystems == null :
                this$supportedOperatingSystems.equals(other$supportedOperatingSystems))
                && this.isMonitoring() == other.isMonitoring();
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $supportedOperatingSystems = this.getSupportedOperatingSystems();
        result = result * PRIME + ($supportedOperatingSystems == null ? 43 : $supportedOperatingSystems.hashCode());
        result = result * PRIME + (this.isMonitoring() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "moe.tristan.Lyrical.model.integration.players.playersimpl.iTunes(name=" + this.getName() + ", supportedOperatingSystems=" + this.getSupportedOperatingSystems() + ", isMonitoring=" + this.isMonitoring() + ")";
    }
}


