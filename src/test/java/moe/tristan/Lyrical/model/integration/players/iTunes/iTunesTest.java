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

import moe.tristan.Lyrical.model.integration.players.playersimpl.iTunes;
import moe.tristan.Lyrical.model.integration.system.SystemUtilities;
import moe.tristan.Lyrical.model.integration.system.macOS.macOS;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class iTunesTest {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(iTunesTest.class);

    @Test
    public void getSong_macOS() throws Exception {
        if (SystemUtilities.CURRENT_PLATFORM instanceof macOS) {
            log.debug(iTunes.getSong_macOS().toString());
        }
    }

}