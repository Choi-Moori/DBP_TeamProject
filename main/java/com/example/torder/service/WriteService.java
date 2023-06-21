package com.example.torder.service;

import com.example.torder.entity.Contents;
import com.example.torder.repository.WriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteService {
    private final WriteRepository writeRepository;

    @Autowired
    public WriteService(WriteRepository writeRepository) {
        this.writeRepository = writeRepository;
    }

    public void saveContents(Contents contents) {
        writeRepository.save(contents);
    }
}
