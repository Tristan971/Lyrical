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
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class ApplicationConfigurationTest {
    private ApplicationConfiguration configuration;

    @Before
    public void setUp() throws Exception {
        configuration = ApplicationConfiguration.getINSTANCE();
    }

    @Test
    public void get() throws Exception {
        Arrays.stream(ConfigurationKey.values())
                .forEach(key -> ApplicationConfiguration.getINSTANCE().get(key));
        Arrays.stream(ConfigurationKey.values())
                .map(key -> "{ " +key.value+" : "+ApplicationConfiguration.getINSTANCE().get(key)+ " }")
                .forEach(System.out::println);
    }

    @Test
    public void getINSTANCE() throws Exception {
        assertNotNull(configuration);
    }

}