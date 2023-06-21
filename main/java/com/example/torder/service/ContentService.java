package com.example.torder.service;

import java.util.List;

import com.example.torder.controller.MatchingForm;
import com.example.torder.domain.Category;
import com.example.torder.domain.Content;
import com.example.torder.repository.ContentRepository;

public class ContentService {
    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    /* 첫 시작 게시글 전체 정보 가져오기 */
    public List<Content> getTotalContent() {
        return contentRepository.getTotalContent();
    }

    public List<Category> getTotalCategory() {
        return contentRepository.getTotalCategory();
    }

    /* 해당 카테고리 모든 정보 가져오기 */
    public List<Content> getCategoryContentInfo(Category category) {
        return contentRepository.getCategoryContentInfo(category);
    }

    /* 해당 게시글 정보만 가져오기 */
    public Content getContentInfo(MatchingForm matchingForm) {
        return contentRepository.getContentInfo(matchingForm);
    }
}
