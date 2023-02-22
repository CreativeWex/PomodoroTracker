package com.bereznev.webapp.repository;
/*
    =====================================
    @project FlashCards
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
