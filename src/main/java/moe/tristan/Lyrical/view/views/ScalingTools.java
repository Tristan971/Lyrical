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

import lombok.Data;

import java.awt.*;

/**
 * Created by tristan9 on 11/7/16.
 */

@Data
public class ScalingTools {
    private static ScalingTools instance = null;

    private final GraphicsDevice gd;

    private ScalingTools() {
        java.awt.Toolkit.getDefaultToolkit();
        this.gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
    }

    public static ScalingTools getScalingTools() {
        if (instance == null) {
            instance = new ScalingTools();
        }
        return instance;
    }

    public double getPercentWidth(double percentage) {
        return gd.getDisplayMode().getWidth() * percentage;
    }

    public double getPercentHeigth(double percentage) {
        return gd.getDisplayMode().getHeight() * percentage;
    }
}
