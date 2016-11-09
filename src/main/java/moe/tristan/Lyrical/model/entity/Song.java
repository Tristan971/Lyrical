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

package moe.tristan.Lyrical.model.entity;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Tristan Deloche on 05/07/2016.
 */
public final class Song {
    @NotNull
    private final String title;
    @NotNull
    private final String artist;
    @NotNull
    private final String album;

    @NotNull
    private final String lyrics;

    Song(@NotNull String title, @NotNull String artist, @NotNull String album, @NotNull String lyrics) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.lyrics = lyrics;
    }

    @Contract(" -> !null")
    public static Song emptySong() {
        return Song.builder()
                .artist("")
                .title("")
                .album("")
                .lyrics("")
                .build();
    }

    public static SongBuilder builder() {
        return new SongBuilder();
    }

    public @NotNull String getTitle() {
        return this.title;
    }

    public @NotNull String getArtist() {
        return this.artist;
    }

    @NotNull
    private String getAlbum() {
        return this.album;
    }

    public @NotNull String getLyrics() {
        return this.lyrics;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Song)) return false;
        final Song other = (Song) o;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (!this$title.equals(other$title)) return false;
        final Object this$artist = this.getArtist();
        final Object other$artist = other.getArtist();
        if (!this$artist.equals(other$artist)) return false;
        final Object this$album = this.getAlbum();
        final Object other$album = other.getAlbum();
        if (!this$album.equals(other$album)) return false;
        final Object this$lyrics = this.getLyrics();
        final Object other$lyrics = other.getLyrics();
        return this$lyrics.equals(other$lyrics);
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + $title.hashCode();
        final Object $artist = this.getArtist();
        result = result * PRIME + $artist.hashCode();
        final Object $album = this.getAlbum();
        result = result * PRIME + $album.hashCode();
        final Object $lyrics = this.getLyrics();
        result = result * PRIME + $lyrics.hashCode();
        return result;
    }

    public String toString() {
        return "moe.tristan.Lyrical.model.entity.Song(title=" + this.getTitle() + ", artist=" + this.getArtist() + ", album=" + this.getAlbum() + ", lyrics=" + this.getLyrics() + ")";
    }

    public static class SongBuilder {
        private @NotNull String title = "";
        private @NotNull String artist = "";
        private @NotNull String album = "";
        private @NotNull String lyrics = "";

        SongBuilder() {
        }

        public Song.SongBuilder title(@NotNull String title) {
            this.title = title;
            return this;
        }

        public Song.SongBuilder artist(@NotNull String artist) {
            this.artist = artist;
            return this;
        }

        public Song.SongBuilder album(@NotNull String album) {
            this.album = album;
            return this;
        }

        public Song.SongBuilder lyrics(@NotNull String lyrics) {
            this.lyrics = lyrics;
            return this;
        }

        public Song build() {
            return new Song(title, artist, album, lyrics);
        }

        public String toString() {
            return "moe.tristan.Lyrical.model.entity.Song.SongBuilder(title=" + this.title + ", artist=" + this.artist + ", album=" + this.album + ", lyrics=" + this.lyrics + ")";
        }
    }
}
