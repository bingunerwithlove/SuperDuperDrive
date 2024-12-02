package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FilesModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@Repository
public interface FilesMapper {

    @Select("SELECT * FROM FILES")
    List<FilesModel> findAll();

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    public FilesModel findOne(int fileid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    public List<FilesModel> findByUserId(int userid);

//    @Select("COUNT(fileid) FROM FILES WHERE filename = #{filename} AND userid = #{userid}")
    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userid}")
    List<FilesModel> findByFileNameandUserId(@Param("filename") String filename, @Param("userid") int userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    public int insertFile(FilesModel file, int userid);
//    int insertFile(@Param("file") FilesModel file, @Param("userid") int userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    public int deleteFile(int fileid);
}
