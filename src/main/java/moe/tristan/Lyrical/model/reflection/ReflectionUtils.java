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

package moe.tristan.Lyrical.model.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by tristan9 on 11/7/16.
 */

@Slf4j
public final class ReflectionUtils {

    public static <S extends Reflectable> S newInstanceOfService(Class<S> serviceClass) {
        try {
            return serviceClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(
                    "Could not access constructor. " +
                    "It needs to be public and imported in case of JDK9",
                    e
            );
        } catch (InvocationTargetException e) {
            log.error(
                    "The "+serviceClass.getName()+" constructor must have a no-arguments " +
                    "implementation and it should be a valid one",
                    e
            );
        } catch (NoSuchMethodException e) {
            log.error("You must define a constructor taking no arguments explicitly.", e);
        }
        throw new RuntimeException("The "+serviceClass.getName()+" could not be initialized. " +
                "This is a grave error and must be fixed.");
    }
}
