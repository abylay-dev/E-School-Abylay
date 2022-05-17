package kz.abylay.eschool.repositories.mapper;

import kz.abylay.eschool.models.Roles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Select("select * from roles where role=#{role}")
    Roles findRole(String role);

    @Insert("insert into roles values (null, #{role})")
    void insertRole(Roles roles);

    @Delete("delete from roles where role=#{role}")
    void deleteRole(String role);
}
