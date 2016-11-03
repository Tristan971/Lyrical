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

package moe.tristan.Lyrical.view;

import moe.tristan.Lyrical.model.entity.Song;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class UIBridgeTest {
    @Test
    public void getInstance() throws Exception {
        Assert.assertNotNull(UIBridge.getInstance());
    }

    @Test
    public void songChanged() throws Exception {
        UIBridge.getInstance().songChanged(Song.emptySong());
        Assert.assertThat(UIBridge.getInstance().title.get(), CoreMatchers.is(""));

        UIBridge.getInstance().songChanged(
                Song.builder()
                        .title("TITLE")
                        .artist("ARTIST")
                        .album("ALBUM")
                        .lyrics("LYRICS\nMORE LYRICS.")
                        .build()
        );
        Assert.assertThat(UIBridge.getInstance().title.get(), CoreMatchers.is("TITLE"));
        Assert.assertThat(UIBridge.getInstance().artist.get(), CoreMatchers.is("ARTIST"));
        Assert.assertThat(UIBridge.getInstance().lyrics.get(), CoreMatchers.is("LYRICS\nMORE LYRICS."));

    }

}