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

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.HashMap;

/**
 * Created by Tristan Deloche on 08/07/2016.
 */
public final class ApplicationConfiguration {
    @NotNull
    private static final ApplicationConfiguration INSTANCE;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ApplicationConfiguration.class);

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

    public
    @NotNull
    static ApplicationConfiguration getINSTANCE() {
        return ApplicationConfiguration.INSTANCE;
    }

    @NotNull
    public static ApplicationConfigurationBuilder builder() {
        return new ApplicationConfigurationBuilder();
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


    public static class ApplicationConfigurationBuilder {
        private HashMap<String, String> configMap;

        ApplicationConfigurationBuilder() {
        }

        @NotNull
        public ApplicationConfiguration.ApplicationConfigurationBuilder configMap(HashMap<String, String> configMap) {
            this.configMap = configMap;
            return this;
        }

        @NotNull
        public ApplicationConfiguration build() {
            return new ApplicationConfiguration(configMap);
        }

        @NotNull
        @Override
        public String toString() {
            return "moe.tristan.Lyrical.model.configuration.ApplicationConfiguration.ApplicationConfigurationBuilder(configMap=" + this.configMap + ")";
        }
    }
}
