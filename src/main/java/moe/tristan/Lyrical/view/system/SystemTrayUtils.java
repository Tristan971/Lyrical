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

package moe.tristan.Lyrical.view.system;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import moe.tristan.Lyrical.util.ResourceUtil;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Created by Tristan Deloche on 10/07/2016.
 *
 * Credits to Jewelsea for the SystemTray JavaFX usage skeleton
 *  @see{https://gist.github.com/jewelsea/e231e89e8d36ef4e5d8a}
 */
public class SystemTrayUtils {

    private static TrayIcon TRAY_ICON = null;
    private boolean isVisible = true;

    public static void initTrayIconWithStage(Stage stage) {
        Platform.setImplicitExit(false);
        try {
            java.awt.Toolkit.getDefaultToolkit();

            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            java.awt.Image image = ImageIO.read(ResourceUtil.getImageResource("macOS/lyrical-trayicon-1X.png"));
            TrayIcon trayIcon = new TrayIcon(image);

            trayIcon.addMouseListener(getMouseListener(stage));
            TRAY_ICON = trayIcon;

            tray.add(trayIcon);

            stage.setOnCloseRequest(SystemTrayUtils::fireClosureEvent);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static MouseListener getMouseListener(Stage stage) {
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getID() == 1903) {
                    java.awt.SystemTray.getSystemTray().remove(java.awt.SystemTray.getSystemTray().getTrayIcons()[0]);
                }

                Platform.runLater(stage.isShowing() ? stage::close : stage::show);
            }
        };
    }

    private static void fireClosureEvent(WindowEvent e) {
        MouseEvent event = new MouseEvent(new java.awt.Label(), 1903, 0, 0, 0, 0, 0, false, 0);
        TRAY_ICON.getMouseListeners()[0].mouseReleased(event);
    }
}
