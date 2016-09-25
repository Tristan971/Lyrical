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

package moe.tristan.Lyrical.model.lyricsproviders;

import lombok.extern.slf4j.Slf4j;
import moe.tristan.Lyrical.model.entity.Song;
import moe.tristan.Lyrical.model.lyricsproviders.services.MusixMatchService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
@Slf4j
public class MusixMatchServiceTest {
    private MusixMatchService service;

    @Before
    public void setUp() throws Exception {
        service = new MusixMatchService("cf95b51764535943eae59b7e94a3f96c");
    }

    @Test
    public void identifySong() throws Exception {
        Song loseYourself = service.identifySong("Lose yourself", "Eminem");
        Assert.assertThat(loseYourself.getLyrics(), containsString("Look"));
        log.debug(loseYourself.toString());
    }

}