package com.example.demo.Repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query("UPDATE Task t SET t.done = TRUE WHERE t.id = :id")
    public void MakeAsDone(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Task t set t.done = FALSE WHERE t.id = :id")
    public void MakeAsFalse(@Param("id") Long id);

    List<Task> findByUserId(Long userId);



}