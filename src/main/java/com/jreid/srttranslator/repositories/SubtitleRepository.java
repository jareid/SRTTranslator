package com.jreid.srttranslator.repositories;

import com.jreid.srttranslator.entities.Subtitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource()
public interface SubtitleRepository extends JpaRepository<Subtitle, Integer>, JpaSpecificationExecutor<Subtitle> {

}
