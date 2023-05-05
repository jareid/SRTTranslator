package com.jreid.srttranslator.services;

import java.util.List;

import com.jreid.srttranslator.entities.Subtitle;
import com.jreid.srttranslator.repositories.SubtitleRepository;
import org.springframework.stereotype.Component;

@Component
public class SubtitleService {

    private final SubtitleRepository subtitleRepository;

    public SubtitleService(SubtitleRepository subtitleRepository) {
        this.subtitleRepository = subtitleRepository;
    }

    public List<Subtitle> getSubtitles() {
        return subtitleRepository.findAll();
    }

    public Subtitle saveSubtitle(Subtitle users) {
        return subtitleRepository.save(users);
    }

}
