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

import lombok.extern.slf4j.Slf4j;
import moe.tristan.Lyrical.model.entity.Song;
import moe.tristan.Lyrical.model.lyricsproviders.services.DummyService;
import moe.tristan.Lyrical.model.reflection.ReflectionUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Tristan Deloche on 08/07/2016.
 */
@Slf4j
public final class LyricsServicesManager {
    private static final LyricsServicesManager instance = new LyricsServicesManager();

    private final Set<Service> registeredServices = new HashSet<>();

    private LyricsServicesManager() {
    }

    public static void registerService(@NotNull Class<? extends Service> serviceClass) {
        Optional<? extends Service> possibleDuplicate =
                instance.registeredServices.stream()
                        .filter(service -> service.getClass() == serviceClass)
                        .findAny();
        if (!possibleDuplicate.isPresent()) {
            instance.registeredServices.add(ReflectionUtils.newInstanceOfService(serviceClass));
            log.info("Correctly registered the " + serviceClass.getSimpleName() + " service.");
        } else {
            log.error(
                    "A " + serviceClass.getName() + " service is already "
                            + "registered. Unregister it first.\n"
                            + "Registered services are : " +
                            instance.registeredServices.stream()
                                    .map(service -> service.getClass().getName())
                                    .collect(Collectors.toList())
            );
        }
    }

    public static void unregisterService(@NotNull Class<? extends Service> serviceClass) {
        final String serviceClassToUnregister = serviceClass.getCanonicalName();
        boolean unregistered = instance.registeredServices.removeIf(service -> service.getClass().getCanonicalName().equals(serviceClassToUnregister));
        if (unregistered) {
            log.info("Correctly unregistered the " + serviceClass.getSimpleName() + " service.");
        } else {
            log.error(
                    "There was no "
                            + serviceClass.getSimpleName()
                            + " service registered. "
                            + "Therefore no service could be unregistered. "
                            + "The currently registered services are : "
                            + instance.registeredServices
            );
        }
    }

    @NotNull
    public static Set<Service> getRegisteredServices() {
        return Collections.unmodifiableSet(new HashSet<>(instance.registeredServices));
    }

    @NotNull
    public static Song identifySong(String title, String artist) {
        Service service = instance.registeredServices.stream().findAny().orElse(new DummyService());
        return service.identifySong(title, artist);
    }
}
