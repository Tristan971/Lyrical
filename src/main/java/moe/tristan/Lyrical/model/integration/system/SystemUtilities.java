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

import sun.jvm.hotspot.utilities.UnsupportedPlatformException;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class SystemUtilities {
    public static OperatingSystem getCurrentOperatingSystem() {
        return getCurrentOperatingSystemImpl(System.getProperty("os.name"));
    }

    private static OperatingSystem getCurrentOperatingSystemImpl(String platformName) {
        System.out.println("Current platform is : "+platformName);
        if (isOSX(platformName))
        {
            return macOS.getINSTANCE();
        } else {
            throw new UnsupportedPlatformException();
        }
    }

    private static boolean isOSX(String platformName) {
        return  platformName.toLowerCase().contains("mac") ||
                platformName.toLowerCase().contains("darwin") ||
                platformName.toLowerCase().contains("osx");
    }
}
