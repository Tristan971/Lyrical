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

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * This is a bit of a hack for now sadly
 */
public class ErrorPane extends AnchorPane {
    public ErrorPane(String cause, String message) {
        super();
        final Label title = new Label(cause);
        final Label errorMsg = new Label(message);

        title.setLayoutX(20);
        title.setLayoutY(20);
        title.setFont(Font.font("sans", 50));
        errorMsg.setLayoutX(20);
        errorMsg.setLayoutY(80);
        errorMsg.setFont(Font.font("sans", 35));
        errorMsg.setWrapText(true);

        this.getChildren().add(title);
        this.getChildren().add(errorMsg);
    }
}
