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

package moe.tristan.Lyrical.model.integration.players;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Tristan Deloche on 09/07/2016.
 */
public final class PlayerSong {
    @NotNull
    private final String title;
    @NotNull
    private final String artist;

    @java.beans.ConstructorProperties({"title", "artist"})
    PlayerSong(@NotNull String title, @NotNull String artist) {
        this.title = title;
        this.artist = artist;
    }

    public static PlayerSong dummyPlayerSong() {
        return PlayerSong.builder()
                .artist("No artist")
                .title("No title")
                .build();
    }

    public static PlayerSongBuilder builder() {
        return new PlayerSongBuilder();
    }

    public @NotNull String getTitle() {
        return this.title;
    }

    public @NotNull String getArtist() {
        return this.artist;
    }

    public String toString() {
        return "moe.tristan.Lyrical.model.integration.players.PlayerSong(title=" + this.getTitle() + ", artist=" + this.getArtist() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PlayerSong)) return false;
        final PlayerSong other = (PlayerSong) o;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (!this$title.equals(other$title)) return false;
        final Object this$artist = this.getArtist();
        final Object other$artist = other.getArtist();
        return this$artist.equals(other$artist);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + $title.hashCode();
        final Object $artist = this.getArtist();
        result = result * PRIME + $artist.hashCode();
        return result;
    }

    public static class PlayerSongBuilder {
        private @NotNull String title = "";
        private @NotNull String artist = "";

        PlayerSongBuilder() {
        }

        public PlayerSong.PlayerSongBuilder title(@NotNull String title) {
            this.title = title;
            return this;
        }

        public PlayerSong.PlayerSongBuilder artist(@NotNull String artist) {
            this.artist = artist;
            return this;
        }

        public PlayerSong build() {
            return new PlayerSong(title, artist);
        }

        public String toString() {
            return "moe.tristan.Lyrical.model.integration.players.PlayerSong.PlayerSongBuilder(title=" + this.title + ", artist=" + this.artist + ")";
        }
    }
}
