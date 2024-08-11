package com.pizza.awesomepizza.scheduling;

import com.pizza.awesomepizza.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemporaryFileDeletion {

    private final FileService fileService;

    @Scheduled(cron = "0 0 0 1 * *")
    public void deleteFiles() {
        log.info("Deleting temporary files");

        fileService.deleteTemporaryFiles();
    }

}
