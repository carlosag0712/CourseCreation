package io.agileintelligence.io.agileintelligence.repository;

import io.agileintelligence.io.agileintelligence.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by carlosarosemena on 2017-03-13.
 */
@Repository
public interface SectionRepository extends JpaRepository<Section, Long>
{
}
