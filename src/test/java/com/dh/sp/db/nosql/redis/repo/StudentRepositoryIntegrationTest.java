package com.dh.sp.db.nosql.redis.repo;


import com.dh.sp.db.nosql.redis.IntegrationTest;
import com.dh.sp.db.nosql.redis.model.Student;
import com.dh.sp.db.nosql.redis.model.TestStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StudentRepositoryIntegrationTest extends IntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void shouldSave(){
        //Given
        final UUID id = UUID.randomUUID();
        final Student student = TestStudent.getInstance(id).build();

        //when
        studentRepository.save(student);

        //then
        Optional<Student> result = studentRepository.findById(id);
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get()).isEqualTo(student);
    }
}
