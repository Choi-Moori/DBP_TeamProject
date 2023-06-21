package com.example.torder.repository;

import java.util.List;

import com.example.torder.controller.MatchingForm;
import com.example.torder.domain.Category;
import com.example.torder.domain.Content;

public interface ContentRepository {

    // 첫 시작 전체 게시글 가져오기
    List<Content> getTotalContent();

    // 첫 시작 전체 카테고리 가져오기
    List<Category> getTotalCategory();

    // 해당 카테고리의 모든 게시글 가져오기
    List<Content> getCategoryContentInfo(Category category);

    // 특정 게시글 정보 가져오기
    Content getContentInfo(MatchingForm matchingForm);
}
