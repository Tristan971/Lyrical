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

package moe.tristan.Lyrical.view.views;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by tristan9 on 11/6/16.
 */

@Slf4j
public class Errors {
    public static void alertWarning(String cause, String message) {
        final ErrorPane errorPane = new ErrorPane(cause, message);
        final Stage stage = new Stage(StageStyle.UTILITY);
        final Scene scene = new Scene(errorPane);
        stage.setScene(scene);

        Platform.runLater(stage::show);
    }

    public static void highDpiOutdatedLinux(double runtimeVersion) {
        log.warn("Java Runtime "+runtimeVersion+" on linux detected. " +
                "HighDPI Will not be supported. Showing alert pane."
        );
        Errors.alertWarning(
                "HighDPI scaling is unsupported on linux before Java 9",
                "You are running the Java runtime version : "+runtimeVersion
                        + ".\n"
                        + "Unfortunately, HighDPI scaling isn't available in Java "
                        + "versions prior to 9 (1.9) on the linux platform.\n"
                        + "Please update your version to "
                        + "9 (1.9) if you need HighDPI support."
        );
    }
}
