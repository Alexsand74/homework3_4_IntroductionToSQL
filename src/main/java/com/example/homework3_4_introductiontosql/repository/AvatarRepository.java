package com.example.homework3_4_introductiontosql.repository;
import com.example.homework3_4_introductiontosql.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(long id);
}

