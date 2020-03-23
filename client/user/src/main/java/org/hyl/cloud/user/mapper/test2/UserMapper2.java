package org.hyl.cloud.user.mapper.test2;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper2 {

    @Insert("insert into user_02(name,age) values(#{name},#{age})")
    void addUser(@Param("name") String name, @Param("age") int age);
}
