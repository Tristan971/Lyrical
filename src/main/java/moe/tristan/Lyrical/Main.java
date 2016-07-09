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

import javafx.application.Application;
import javafx.stage.Stage;
import moe.tristan.Lyrical.model.lyricsproviders.LyricsServicesManager;
import moe.tristan.Lyrical.model.lyricsproviders.MusixMatchService;

import java.util.Arrays;

/**
 * Created by Tristan Deloche on 05/07/2016.
 */
public final class Main extends Application {
    public static boolean DEV_MODE = false;
    public static Stage MAIN_STAGE = null;

    public static void main(String... args) {
        DEV_MODE = Arrays.stream(args)
                .filter(s -> s.equals("DEV"))
                .findAny()
                .isPresent();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MAIN_STAGE = primaryStage;
        primaryStage.show();

        LyricsServicesManager.getInstance().registerService(MusixMatchService.class);
    }
}
