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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import moe.tristan.Lyrical.model.configuration.ApplicationConfiguration.ConfigurationKey;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public final class ApplicationConfigurationReader {
    @NotNull
    private static final Gson gson = new Gson();

    public static ApplicationConfiguration dummy() {
        HashMap<String, String> config = new HashMap<>();
        Arrays.stream(ConfigurationKey.values())
                .forEach(key -> config.put(key.value, ""));

        return ApplicationConfiguration.builder()
                .configMap(config)
                .build();
    }

    public static ApplicationConfiguration readDefaultConfiguration() {
        InputStream profileStream = ApplicationConfigurationReader.class
                .getClassLoader()
                .getResourceAsStream("DefaultProfile.json");

        TypeToken<HashMap<String, String>> hashMapTypeToken =
                new TypeToken<HashMap<String, String>>() {};

        System.out.println("Reading the default configuration profile.");
        final HashMap<String, String> configMap =
                gson.fromJson(new InputStreamReader(profileStream), hashMapTypeToken.getType());

        Arrays.stream(ConfigurationKey.values())
                .forEach(key -> configMap.putIfAbsent(key.value, ""));

        System.out.println("Read configuration is : "+configMap);
        return ApplicationConfiguration.builder()
                .configMap(configMap)
                .build();
    }
}
