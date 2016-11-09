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

package moe.tristan.Lyrical.model.integration.system.Windows;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.DispatchEvents;
import moe.tristan.Lyrical.model.integration.system.OperatingSystem;

/**
 * Class modeling a Windows NT platform.
 * It provides platform-dependant functionnalities.
 */
public class WindowsNT implements OperatingSystem {
    private static WindowsNT INSTANCE = null;
    private final String name = "Windows";

    private WindowsNT() {
        INSTANCE = this;
    }

    public static WindowsNT getInstance() {
        return INSTANCE == null ? new WindowsNT() : INSTANCE;
    }

    public static void initJacobEvents(@SuppressWarnings("SameParameterValue") String identifier, Object sink) {
        ActiveXComponent activeXComponent = new ActiveXComponent(identifier);
        Dispatch controller = activeXComponent.getObject();

        //The controller automagically binds to this somehow idk
        @SuppressWarnings("unused")
        DispatchEvents COMDispatchEvents = new DispatchEvents(controller, sink);
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof WindowsNT)) return false;
        final WindowsNT other = (WindowsNT) o;
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

    private boolean canEqual(Object other) {
        return other instanceof WindowsNT;
    }

    public String toString() {
        return "moe.tristan.Lyrical.model.integration.system.Windows.WindowsNT(name=" + this.getName() + ")";
    }
}
