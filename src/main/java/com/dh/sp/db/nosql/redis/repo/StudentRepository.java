package com.dh.sp.db.nosql.redis.repo;

import com.dh.sp.db.nosql.redis.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {
}
