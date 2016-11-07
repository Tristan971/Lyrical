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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents an unsupported platform.
 * The name fields will always have the value passed in argument.
 * It will be equivalent to System.getProperty("os.name")
 */

@Data
@Slf4j
public class DummySystem implements OperatingSystem {

    private final String name;

    @Override
    public String getName() {
        log.warn("Unknown operating system! Report this : "+System.getProperty("os.name"));
        return System.getProperty("os.name");
    }
}
