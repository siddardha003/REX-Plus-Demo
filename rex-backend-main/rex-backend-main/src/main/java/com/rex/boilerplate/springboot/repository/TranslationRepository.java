package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TranslationRepository extends JpaRepository<Translation, Long> {
    
    // ✅ Get ALL keys for a component
    @Query("SELECT t FROM Translation t WHERE t.component = :component")
    List<Translation> findByComponent(@Param("component") String component);
    
    // ✅ Get single key for component
    Optional<Translation> findByComponentAndKey(String component, String key);
}
