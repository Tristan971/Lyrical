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

package moe.tristan.Lyrical.model.integration.players.iTunes;

import moe.tristan.Lyrical.model.integration.players.Player;
import moe.tristan.Lyrical.model.integration.players.PlayerSong;
import moe.tristan.Lyrical.model.integration.system.OperatingSystem;
import moe.tristan.Lyrical.model.integration.system.macOS;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Tristan Deloche on 05/07/2016.
 */
public final class iTunes implements Player {
    @NotNull
    @Contract(pure = true)
    public String getName() {
        return "iTunes";
    }

    @Contract("null -> fail")
    @Override
    public PlayerSong getCurrentlyPlayedSong(OperatingSystem system) {
        if (system instanceof macOS) {
            return getSong_macOS();
        } else {
            throw new NotImplementedException();
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

        String name = macOS.getINSTANCE().runApplescript(scriptForName);

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

        String artist = macOS.getINSTANCE().runApplescript(scriptForArtist);

        if (!name.equals("NOT_FOUND")) {
            System.out.println("Name of currently playing song : "+name);
        }
        if (!artist.equals("NOT_FOUND")) {
            System.out.println("Name of currently playing artist : "+artist);
        }

        return PlayerSong.builder()
                .title(name)
                .artist(artist)
                .build();
    }
}
