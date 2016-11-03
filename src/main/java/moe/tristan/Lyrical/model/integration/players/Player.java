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

package moe.tristan.Lyrical.model.integration.players;

import moe.tristan.Lyrical.model.integration.system.OperatingSystem;
import moe.tristan.Lyrical.model.monitoring.Monitorable;

import java.util.Set;

/**
 * Interface for players to be working all in a meaningful way and be
 * handled by the monitor service managers
 */
public interface Player extends Monitorable {
    String getName();

    Set<OperatingSystem> getSupportedOperatingSystems();

    PlayerSong getCurrentlyPlayedSong();
}
