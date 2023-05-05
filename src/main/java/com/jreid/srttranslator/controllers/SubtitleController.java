package com.jreid.srttranslator.controllers;

import java.util.List;

import com.jreid.srttranslator.entities.Subtitle;
import com.jreid.srttranslator.links.SubtitleLinks;
import com.jreid.srttranslator.services.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class SubtitleController {

    @Autowired
    SubtitleService subtitleService;

    @GetMapping(path = SubtitleLinks.LIST_SUBTITLES)
    public ResponseEntity<?> listUsers() {
        log.info("UsersController:  list users");
        List<Subtitle> resource = subtitleService.getSubtitles();
        return ResponseEntity.ok(resource);
    }

    @PostMapping(path = SubtitleLinks.ADD_SUBTITLE)
    public ResponseEntity<?> saveUser(@RequestBody Subtitle subtitle) {
        log.info("UsersController:  list users");
        Subtitle resource = subtitleService.saveSubtitle(subtitle);
        return ResponseEntity.ok(resource);
    }
}
