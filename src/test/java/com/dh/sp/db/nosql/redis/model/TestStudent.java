package com.dh.sp.db.nosql.redis.model;

import com.dh.sp.db.nosql.redis.util.TestUtil;

import java.util.UUID;

public class TestStudent extends Student.StudentBuilder{

    public static Student.StudentBuilder getInstance(final UUID seed){
        return Student.builder()
                .id(seed)
                .grade(1)
                .name(TestUtil.genField("name", seed));
    }
}
