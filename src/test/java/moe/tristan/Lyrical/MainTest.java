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

package moe.tristan.Lyrical;

import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Tristan Deloche on 09/07/2016.
 *
 * Ignore these tests for now since the IoC is making them a bit tough to
 * implement decently. Oh well, we'll see later.
 */
public class MainTest {
    @Test
    public void main() throws Exception {
        Thread appThread = new Thread(() -> Main.main("DEV"));
        appThread.start();
        new ScheduledThreadPoolExecutor(1).schedule(
                () -> {
                        assertThat(Main.DEV_MODE, is(true));
                    //noinspection deprecation
                    appThread.stop();
                },
                3,
                TimeUnit.SECONDS
        );
    }
}