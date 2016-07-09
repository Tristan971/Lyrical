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

package moe.tristan.Lyrical.model.integration.system;

import apple.applescript.AppleScriptEngine;
import lombok.Getter;

import javax.script.ScriptException;

/**
 * Created by Tristan Deloche on 05/07/2016.
 */
public final class macOS implements OperatingSystem {
    @Getter
    private static final macOS INSTANCE = new macOS();

    private static final AppleScriptEngine appleScriptEngine = new AppleScriptEngine();

    public String runApplescript(String script) {
        try {
            System.out.println(appleScriptEngine);

            String returnedData;
            if ((returnedData = (String) appleScriptEngine.eval(script)) != null) {
                return returnedData;
            }
        } catch (ScriptException e) {
            System.err.println("Script returned void instead of an instance of Object");
        }
        return "NOT_FOUND";
    }

    @Override
    public void updateSystemDisplay() {

    }
}
