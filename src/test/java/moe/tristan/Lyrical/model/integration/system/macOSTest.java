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

package moe.tristan.Lyrical.model.integration.system;

import moe.tristan.Lyrical.model.integration.system.macOS.macOS;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class macOSTest {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(macOSTest.class);
    private macOS macOSInstance;

    @Before
    public void setup() {
        macOSInstance = macOS.getInstance();
        assertNotNull(macOS.getInstance());
    }

    @Test
    public void runApplescript() throws Exception {
        if (SystemUtilities.CURRENT_PLATFORM instanceof macOS) {
            macOSInstance.runAppleScriptNew("say \"AppleScript test.\"");
        } else {
            log.debug("Not on OS X. No need to test for AppleScript");
        }
    }

    @Test
    public void updateSystemDisplay() throws Exception {

    }

}