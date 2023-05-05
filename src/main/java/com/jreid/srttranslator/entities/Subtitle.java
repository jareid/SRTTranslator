package com.jreid.srttranslator.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Entity
@NoArgsConstructor(force = true)
public class Subtitle {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    @NotNull(/*message="{NotNull.Subtitles.name}"*/)
    private String name;

    @Column
    @NotNull(/*message="{NotNull.Subtitles.name}"*/)
    private String series;

    @Column
    @NotNull(/*message="{NotNull.Subtitles.name}"*/)
    private Integer episodeId;

    @Column
    @NotNull(/*message="{NotNull.Subtitles.name}"*/)
    private Integer seasonId;

    @Column
    @NotNull(/*message="{NotNull.Subtitles.language}"*/)
    private String language;

    @Column
    @NotNull(/*message="{NotNull.Subtitles.subtitle}"*/)
    private SubtitleContents subtitleContents;
}
