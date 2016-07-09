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

package moe.tristan.Lyrical.model.entity;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Contract;

/**
 * Created by Tristan Deloche on 05/07/2016.
 */
@Builder
@Data
public final class Song {
    private final String title;
    private final String artist;
    private final String album;

    private final String lyrics;

    @Contract(" -> !null")
    public static Song emptySong() {
        return Song.builder()
                .artist("")
                .title("")
                .album("")
                .lyrics("")
                .build();
    }
}