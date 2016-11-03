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

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Tristan Deloche on 08/07/2016.
 */
@Builder
@Slf4j
public final class ApplicationConfiguration {
    @NotNull
    @Getter
    private static final ApplicationConfiguration INSTANCE;

    static {
        INSTANCE = readConfiguration();
    }

    private final HashMap<String, String> configMap;

    private ApplicationConfiguration(HashMap<String, String> configMap) {
        this.configMap = configMap;
    }

    @NotNull
    private static ApplicationConfiguration readConfiguration() {
        return defaultConfiguration();
    }

    @NotNull
    private static ApplicationConfiguration defaultConfiguration() {
        return ApplicationConfigurationReader.readDefaultConfiguration();
    }

    @NotNull
    public String get(@NotNull ConfigurationKey key) {
        return getImpl(key.value);
    }

    @NotNull
    private String getImpl(String key) {
        String a = configMap.get(key);

        if (a == null) {
            log.error("A key " + key + " was not found in the configuration : " + configMap.toString());
            return "NULL";
        } else {
            return a;
        }
    }

    public enum ConfigurationKey {
        MUSIXMATCH_API_KEY("MUSIXMATCH_API_KEY");

        public final String value;

        ConfigurationKey(String s) {
            value = s;
        }
    }


}
