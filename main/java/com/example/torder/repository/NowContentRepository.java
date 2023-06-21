package com.example.torder.repository;

import com.example.torder.entity.ContentEntity;

public interface NowContentRepository {

    ContentEntity findContentById(int contentId);
}
