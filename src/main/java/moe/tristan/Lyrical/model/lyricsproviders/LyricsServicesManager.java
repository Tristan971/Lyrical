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

package moe.tristan.Lyrical.model.lyricsproviders;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tristan Deloche on 08/07/2016.
 */
public class LyricsServicesManager {
    private static final LyricsServicesManager INSTANCE = new LyricsServicesManager();

    private Set<Service> registeredServices = new HashSet<>();

    public void registerService(Class<Service> serviceClass) {

        try {
            if (registeredServices.stream().filter(service -> service.getClass() == serviceClass).count() != 0) {
                registeredServices.add(serviceClass.newInstance());
            } else {
                System.err.println(
                        "A " + serviceClass.getName() + " service is already "
                                + "registered. Unregister it first.\n"
                                + "Registered services are : " +
                                registeredServices.stream()
                                        .map(service -> service.getClass().getName())
                );
            }
        } catch (IllegalAccessException | InstantiationException e) {
            System.err.println("An " + e.getClass() + " was thrown while trying to instantiate a " + serviceClass.getName() + " service");
            System.err.println(e.getMessage());
        }
    }

    public void unregisterService(Class<Service> serviceClass) {
        final String serviceClassToUnregister = serviceClass.getCanonicalName();
        registeredServices.removeIf(service -> service.getClass().getCanonicalName().equals(serviceClassToUnregister));
    }

    public LyricsServicesManager getInstance() {
        return INSTANCE;
    }

    public Set<Service> getRegisteredServices() {
        return Collections.unmodifiableSet(new HashSet<>(registeredServices));
    }
}
