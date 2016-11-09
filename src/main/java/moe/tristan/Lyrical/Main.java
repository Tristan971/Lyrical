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

package moe.tristan.Lyrical;

import moe.tristan.Lyrical.view.core.GUILauncher;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Arrays;

/**
 * Main class
 */
public final class Main {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static boolean DEV_MODE = false;

    public static void main(@NotNull String... args) {
        DEV_MODE = Arrays.stream(args)
                .anyMatch(s -> s.equals("DEV"));
        if (DEV_MODE) {
            log.debug("Developer mode enabled.");
        }

        GUILauncher.main(args);
    }
}
