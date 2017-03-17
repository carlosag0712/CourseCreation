package io.agileintelligence.io.agileintelligence.repository;

import io.agileintelligence.io.agileintelligence.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by carlosarosemena on 2017-03-11.
 */
public interface CourseRepository extends JpaRepository<Course, Long>
{
    @Query("select c from Course c where c.user.id = ?#{principal.id} or 1=?#{hasRole('ROLE_ADMIN')? 1 : 0}")
    public List<Course> findAll();

}
