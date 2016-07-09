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

package moe.tristan.Lyrical.model.configuration;

import moe.tristan.Lyrical.model.configuration.ApplicationConfiguration.ConfigurationKey;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class ApplicationConfigurationReader {
    public static ApplicationConfiguration dummy() {
        HashMap<String, String> config = new HashMap<>();
        Arrays.stream(ConfigurationKey.values())
                .forEach(key -> config.put(key.value, ""));

        return ApplicationConfiguration.builder()
                .configMap(config)
                .build();
    }
}
