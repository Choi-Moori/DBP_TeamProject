package com.example.torder.service;

import com.example.torder.domain.NowContent;
import com.example.torder.entity.ContentEntity;
import com.example.torder.repository.NowContentRepository;

public class NowContentService {
    private final NowContentRepository nowcontentRepository;

    public NowContentService(NowContentRepository nowcontentRepository) {
        this.nowcontentRepository = nowcontentRepository;
    }

    public NowContent findContent(int contentId) {
        ContentEntity contentEntity = nowcontentRepository.findContentById(contentId);
        NowContent content = new NowContent();

        content.setPKContentId(contentEntity.getPKContentId());
        content.setCategoryId(contentEntity.getCategoryId());
        content.setTitle(contentEntity.getTitle());
        content.setBody(contentEntity.getBody());
        content.setEndTime(contentEntity.getEndTime());
        content.setLocation(contentEntity.getLocation());
        content.setContentState(contentEntity.getContentState());

        return content;
    }
}
