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
import lombok.Data;
import moe.tristan.Lyrical.model.integration.players.playersimpl.iTunesCOMEvents;
import moe.tristan.Lyrical.model.integration.system.OperatingSystem;

/**
 * Class modeling a Windows NT platform.
 * It provides platform-dependant functionnalities.
 */
@Data
public class WindowsNT implements OperatingSystem {
    public static final WindowsNT INSTANCE = new WindowsNT();
    private final String name = "Windows";

    public static DispatchEvents initJacobEvents(String identifier, Object sink) {
        ActiveXComponent activeXComponent = new ActiveXComponent(identifier);
        Dispatch controller = activeXComponent.getObject();
        return new DispatchEvents(controller, sink);
    }
}
