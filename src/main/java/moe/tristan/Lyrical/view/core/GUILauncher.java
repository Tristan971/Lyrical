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

package moe.tristan.Lyrical.view.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import moe.tristan.Lyrical.Main;
import moe.tristan.Lyrical.model.integration.players.playersimpl.iTunes;
import moe.tristan.Lyrical.model.lyricsproviders.LyricsServicesManager;
import moe.tristan.Lyrical.model.lyricsproviders.services.MusixMatchService;
import moe.tristan.Lyrical.model.monitoring.PlayerMonitorService;
import moe.tristan.Lyrical.view.system.SystemTrayUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class GUILauncher extends Application {

    public static void main(String... args) {
        initAWT();
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        LyricsServicesManager.registerService(MusixMatchService.class);
        PlayerMonitorService.startMonitoringPlayer(iTunes.class);
        genericStart(primaryStage);
    }

    private static void initAWT() {
        System.setProperty("apple.awt.UIElement", "true");
        java.awt.Toolkit.getDefaultToolkit();
    }

    private static void genericStart(@NotNull Stage primaryStage) {
        Scene mainScene;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    Main.class.getClassLoader()
                            .getResource(
                                    "moe/tristan/Lyrical/view/views/RootView.fxml"
                            )
            );
            mainScene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            mainScene = new Scene(new AnchorPane(new Label("Could not find FXML.")));
        }
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Lyrical - v0.2 beta");
        primaryStage.show();

        if (java.awt.SystemTray.isSupported()) {
            SystemTrayUtils.initTrayIconWithStage(primaryStage);
        }
    }
}
