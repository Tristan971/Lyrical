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

package moe.tristan.Lyrical.view.views;

import javafx.stage.Screen;
import org.slf4j.Logger;

/**
 * This is intended as a utility class. Will be used once I refactor the static
 * sizes in the code.
 */
@SuppressWarnings("WeakerAccess")
public class ScalingTools {
    private static final Logger log;
    private static double scalingMultiplier = 0;

    static {
        log = org.slf4j.LoggerFactory.getLogger(ScalingTools.class);
    }

    public static double getScalingMultiplier() {
        if (scalingMultiplier == 0) {
            scalingMultiplier = Screen.getPrimary().getDpi() / 96.0;
            log.debug("Detected mainScreen with : " + scalingMultiplier * 96.0 + " DPI. Scaling factor : " + scalingMultiplier);
        }
        return scalingMultiplier;
    }

    public static double getScaledLength(double lengthPixels) {
        return getScalingMultiplier() * lengthPixels;
    }

    public static double getScaledFontSize(double fontSize) {
        return fontSize * getScalingMultiplier();
    }
}
