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

package moe.tristan.Lyrical.model.integration.system.macOS;

import moe.tristan.Lyrical.model.integration.system.OperatingSystem;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class representing the macOS system implementation.
 * It offers some platform-dependant functionnalities.
 */
public final class macOS implements OperatingSystem {
    private static macOS INSTANCE = null;
    private final String name = "macOS";

    private macOS() {
        INSTANCE = this;
    }

    public static macOS getInstance() {
        return INSTANCE == null ? new macOS() : INSTANCE;
    }

    @NotNull
    public String runAppleScriptNew(String script) {
        Runtime runtime = Runtime.getRuntime();
        String[] args = {"osascript", "-e", script};
        try {
            Process process = runtime.exec(args);
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));

            String line = reader.lines().findFirst().orElse("null");
            reader.close();
            return line.equals("null") ? OperatingSystem.ERROR_MESSAGE() : line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return OperatingSystem.ERROR_MESSAGE();
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof macOS)) return false;
        final macOS other = (macOS) o;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        return this$name == null ? other$name == null : this$name.equals(other$name);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "moe.tristan.Lyrical.model.integration.system.macOS.macOS(name=" + this.getName() + ")";
    }
}
