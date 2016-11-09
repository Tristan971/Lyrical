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

import moe.tristan.Lyrical.model.integration.system.Linux.Linux;
import moe.tristan.Lyrical.model.integration.system.Windows.WindowsNT;
import moe.tristan.Lyrical.model.integration.system.macOS.macOS;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

/**
 * Class providing utilities for platform detections
 */
public class SystemUtilities {

    public static final OperatingSystem CURRENT_PLATFORM;
    private static final Logger log;

    static {
        log = org.slf4j.LoggerFactory.getLogger(SystemUtilities.class);
        CURRENT_PLATFORM = getCurrentOperatingSystem(System.getProperty("os.name"));
    }

    private static @NotNull OperatingSystem getCurrentOperatingSystem(@NotNull String platformName) {
        log.debug("Detecting current operating system" + platformName);
        log.info("Current platform raw name : " + platformName);
        if (isOSX(platformName)) {
            log.info("Platform detected as macOS");
            return macOS.getInstance();
        } else if (isWindows(platformName)) {
            loadNativeLibraries(WindowsNT.getInstance());
            log.info("Platform detected as Windows");
            return WindowsNT.getInstance();
        } else if (isLinux(platformName)) {
            log.info("Platform detected as Linux : " + Linux.getInstance().getName());
            return Linux.getInstance();
        } else {
            log.error("Platform unsupported as of right now => " + platformName);
            return new DummySystem(platformName);
        }
    }

    private static void loadNativeLibraries(OperatingSystem operatingSystem) {
        if (operatingSystem instanceof WindowsNT) {
            String dataModel = System.getProperty("sun.arch.data.model");

            // I manage my shit. This /will/ work. I test for it.
            //noinspection ConstantConditions
            String nativePath = SystemUtilities.class.getClassLoader()
                    .getResource("native")
                    .getPath();

            if (dataModel.contains("32")) {
                nativePath += "/jacob-1.18-x86.dll";
                System.load(nativePath);
                log.info("Loaded JACOB 1.18 32 bits at : " + nativePath);
            } else {
                nativePath += "/jacob-1.18-x64.dll";
                System.load(nativePath);
                log.info("Loaded JACOB 1.18 64 bits at : " + nativePath);
            }
        }
    }

    private static boolean isOSX(@NotNull String platformName) {
        return platformName.toLowerCase().contains("mac") ||
                platformName.toLowerCase().contains("darwin") ||
                platformName.toLowerCase().contains("osx");
    }

    private static boolean isWindows(@NotNull String platformName) {
        return platformName.toLowerCase().contains("windows") ||
                platformName.toLowerCase().contains("nt");
    }

    private static boolean isLinux(@NotNull String platformName) {
        return platformName.toLowerCase().contains("linux");
    }
}
