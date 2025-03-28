package org.codenova.start.repository;

import org.apache.ibatis.annotations.Mapper;
import org.codenova.start.entity.Comment;

import java.util.List;

@Mapper
public interface CommentRepository {
    public int create(Comment comment);
    public List<Comment> findByIsoCode(String isoCode);
}
