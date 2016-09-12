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

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import lombok.extern.slf4j.Slf4j;
import moe.tristan.Lyrical.model.integration.players.PlayerSong;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tdelo on 09/09/2016.
 */
@Slf4j
public final class iTunesCOMEvents {

    public static PlayerSong currentPlayerSong = PlayerSong.dummyPlayerSong();

    public void OnPlayerPlayEvent(Variant[] args) {
        extractArtistAndTrackName(args);
    }

    public void OnPlayerPlayingTrackChangedEvent(Variant[] args) {
        List<String> argsStringRep = Arrays.stream(args)
                .map(Variant::getString)
                .collect(Collectors.toList());
        log.debug(argsStringRep.toString());
        extractArtistAndTrackName(args);
    }

    private static void extractArtistAndTrackName(Variant[] args) {
        Dispatch event = args[0].getDispatch();

        log.info("Artist: " + Dispatch.get(event, "Artist"));
        log.info("Name: " + Dispatch.get(event, "Name"));
    }
}
