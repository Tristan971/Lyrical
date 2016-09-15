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

import moe.tristan.Lyrical.model.lyricsproviders.services.MusixMatchService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class LyricsServicesManagerTest {


    @Test
    public void registerService() throws Exception {
        LyricsServicesManager.unregisterService(MusixMatchService.class);
        LyricsServicesManager.registerService(MusixMatchService.class);
    }

    @Test
    public void unregisterService() throws Exception {
        LyricsServicesManager.registerService(MusixMatchService.class);
        LyricsServicesManager.unregisterService(MusixMatchService.class);
    }

    @Test
    public void registerAndUnregister() {
        for (int i = 0; i < 5; i++) {
            LyricsServicesManager.unregisterService(MusixMatchService.class);
            LyricsServicesManager.registerService(MusixMatchService.class);
        }
    }

    @Test
    public void getRegisteredServices() throws Exception {
        registerService();
        System.out.println(LyricsServicesManager.getRegisteredServices());
        assertThat(
                LyricsServicesManager.getRegisteredServices(),
                hasItem(instanceOf(MusixMatchService.class))
        );
    }

}