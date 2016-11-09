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

import org.slf4j.Logger;

/**
 * Represents an unsupported platform.
 * The name fields will always have the value passed in argument.
 * It will be equivalent to System.getProperty("os.name")
 */

public class DummySystem implements OperatingSystem {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DummySystem.class);
    private final String name;

    @java.beans.ConstructorProperties({"name"})
    public DummySystem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        log.warn("Unknown operating system! Report this : "+System.getProperty("os.name"));
        return System.getProperty("os.name");
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DummySystem)) return false;
        final DummySystem other = (DummySystem) o;
        if (!other.canEqual(this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        return this$name == null ? other$name == null : this$name.equals(other$name);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean canEqual(Object other) {
        return other instanceof DummySystem;
    }

    public String toString() {
        return "moe.tristan.Lyrical.model.integration.system.DummySystem(name=" + this.getName() + ")";
    }
}
