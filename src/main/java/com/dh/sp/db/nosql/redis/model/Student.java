package com.dh.sp.db.nosql.redis.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Student")
public class Student implements Serializable {

    @Id
    private UUID id;
    private String name;
    private Integer grade;

}
