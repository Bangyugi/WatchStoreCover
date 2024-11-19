package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.CommentRequest;
import com.group2.watchstorecover.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequest commentRequest);
    void updateComment(@MappingTarget Comment comment, CommentRequest commentRequest);
}
