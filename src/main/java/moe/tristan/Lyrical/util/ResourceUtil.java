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

package moe.tristan.Lyrical.util;

import moe.tristan.Lyrical.Main;
import org.jetbrains.annotations.Nullable;

import java.net.URL;

/**
 * Created by Tristan Deloche on 11/07/2016.
 */
@SuppressWarnings("WeakerAccess")
public class ResourceUtil {
    @Nullable
    public static URL getResource(String resourcePath) {
        return Main.class.getClassLoader().getResource(resourcePath);
    }

    @Nullable
    public static URL getImageResource(@SuppressWarnings("SameParameterValue") String image) {
        return getResource("images/"+image);
    }
}
