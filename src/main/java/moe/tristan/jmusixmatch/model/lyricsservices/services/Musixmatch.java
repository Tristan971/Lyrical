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

package moe.tristan.jmusixmatch.model.lyricsservices.services;

import moe.tristan.jmusixmatch.model.language.ErrorMessages;
import moe.tristan.jmusixmatch.model.lyricsservices.LyricsService;
import moe.tristan.jmusixmatch.model.lyricsservices.services.configuration.MusixmatchConfiguration;
import moe.tristan.jmusixmatch.model.lyricsservices.services.configuration.ServiceConfiguration;

/**
 * Created by Tristan Deloche on 05/07/2016.
 */
public class Musixmatch implements LyricsService {

    private final ServiceConfiguration<? extends MusixmatchConfiguration> configuration;

    private Musixmatch(ServiceConfiguration<? extends MusixmatchConfiguration> configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getServiceName() {
        return configuration.getServiceName();
    }

    @Override
    public String findSongUniqueIdentifier(String... songDescription) {
        return ErrorMessages.notFoundFor(this);
    }

    @Override
    public String findSongLyrics(String identifier) {
        return null;
    }
}
