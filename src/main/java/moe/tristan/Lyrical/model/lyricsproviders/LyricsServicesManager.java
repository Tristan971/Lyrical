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

import moe.tristan.Lyrical.model.entity.Song;
import moe.tristan.Lyrical.model.lyricsproviders.services.DummyService;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Tristan Deloche on 08/07/2016.
 */
public final class LyricsServicesManager {
    private static final LyricsServicesManager INSTANCE = new LyricsServicesManager();
    public static LyricsServicesManager getInstance() {
        return INSTANCE;
    }

    private final Set<Service> registeredServices = new HashSet<>();

    private LyricsServicesManager() {}

    public void registerService(Class<? extends Service> serviceClass) {

        try {
            Optional<? extends Service> possibleDuplicate = registeredServices.stream().filter(service -> service.getClass() == serviceClass).findAny();
            if (!possibleDuplicate.isPresent()) {
                registeredServices.add(serviceClass.newInstance());
                System.out.println("Correctly registered the "+serviceClass.getSimpleName()+" service.");
            } else {
                System.err.println(
                        "A " + serviceClass.getName() + " service is already "
                                + "registered. Unregister it first.\n"
                                + "Registered services are : " +
                                registeredServices.stream()
                                        .map(service -> service.getClass().getName())
                                        .collect(Collectors.toList())
                );
            }
        } catch (IllegalAccessException | InstantiationException e) {
            System.err.println("An " + e.getClass() + " was thrown while trying to instantiate a " + serviceClass.getName() + " service");
            System.err.println(e.getMessage());
        }
    }

    public void unregisterService(Class<? extends Service> serviceClass) {
        final String serviceClassToUnregister = serviceClass.getCanonicalName();
        boolean unregistered = registeredServices.removeIf(service -> service.getClass().getCanonicalName().equals(serviceClassToUnregister));
        if (unregistered) {
            System.out.println("Correctly unregistered the "+serviceClass.getSimpleName()+" service.");
        } else {
            System.err.println(
                    "There was no "
                    +serviceClass.getSimpleName()
                    +" service registered. "
                    + "Therefore no service could be unregistered. "
                    + "The currently registered services are : "
                    +registeredServices
            );
        }

    }

    @NotNull
    public Set<Service> getRegisteredServices() {
        return Collections.unmodifiableSet(new HashSet<>(registeredServices));
    }

    @NotNull
    public Song identifySong(String title, String artist) {
        Service service = registeredServices.stream().findAny().orElse(new DummyService());
        return service.identifySong(title, artist);
    }
}
