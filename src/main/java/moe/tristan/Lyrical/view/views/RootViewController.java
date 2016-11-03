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

import com.sun.javafx.scene.control.skin.Utils;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import moe.tristan.Lyrical.view.UIBridge;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public class RootViewController {
    private static final double MAXWIDTH = 230;
    // Smaller is faster
    private static final double MARQUEE_LENGTH_FACTOR = 40;
    private static RootViewController instance = null;
    @FXML
    private Text titleText;
    @FXML
    private Text artistText;
    @FXML
    private Label lyricsLabel;

    public RootViewController() {
        instance = this;
    }

    private static String computeClippedText(Text text) {
        try {
            Method computeClippedTextImpl = Utils.class.getDeclaredMethod(
                    "computeClippedText",
                    Font.class,
                    String.class,
                    double.class,
                    OverrunStyle.class,
                    String.class
            );
            computeClippedTextImpl.setAccessible(true);
            return (String) computeClippedTextImpl.invoke(
                    instance,
                    text.getFont(),
                    text.getText(),
                    MAXWIDTH,
                    OverrunStyle.ELLIPSIS,
                    "..."
            );
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return text.getText();
    }

    private static void marqueeLongTexe(Text text) {
        Runnable transitionAction = getMarqueeTransition(text);
        Platform.runLater(transitionAction);
    }

    @NotNull
    private static Runnable getMarqueeTransition(Text text) {
        double initialMinX = text.getX();
        double initialEndX = text.getBoundsInParent().getMaxX();
        double textLen = initialEndX - initialMinX;
        double overrunLen = textLen - 230;
        double goalX = initialMinX - overrunLen;

        TranslateTransition marquee = new TranslateTransition(
                new Duration(overrunLen * MARQUEE_LENGTH_FACTOR),
                text
        );

        marquee.setFromX(initialMinX);
        marquee.setToX(initialMinX - overrunLen);

        TranslateTransition returnTransition = new TranslateTransition(
                new Duration(overrunLen * MARQUEE_LENGTH_FACTOR),
                text
        );

        returnTransition.setFromX(goalX);
        returnTransition.setToX(initialMinX);

        return () -> {
            marquee.setOnFinished(e -> returnTransition.playFromStart());
            returnTransition.setOnFinished(e -> text.setText(computeClippedText(text)));
            marquee.playFromStart();
        };
    }

    // It is called by JavaFX
    @SuppressWarnings("unused")
    public void initialize() {
        titleText.setText(UIBridge.getInstance().title.get());
        UIBridge.getInstance().title.addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                titleText.setText(newValue);
                if (isTextClipped(titleText)) {
                    marqueeLongTexe(titleText);
                }
            });
        });
        artistText.setText(UIBridge.getInstance().artist.get());
        UIBridge.getInstance().artist.addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                artistText.setText(newValue);
                if (isTextClipped(artistText)) {
                    marqueeLongTexe(artistText);
                }
            });
        });
        lyricsLabel.setText(UIBridge.getInstance().lyrics.get());
        UIBridge.getInstance().lyrics.addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> lyricsLabel.setText(newValue));
        });
    }

    private boolean isTextClipped(@NotNull Text text) {
        return text.getBoundsInParent().getWidth() > MAXWIDTH;
    }
}
