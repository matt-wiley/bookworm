package com.wileymab.bookworm.api.controllers;

import com.wileymab.bookworm.api.controllers.utils.RestCallHandler;
import com.wileymab.bookworm.api.interfaces.AuthorServiceInterface;
import com.wileymab.bookworm.api.interfaces.TitleServiceInterface;
import com.wileymab.bookworm.api.model.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/titles")
public class TitlesController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorsController.class);

    private final TitleServiceInterface titleService;


    public TitlesController(TitleServiceInterface titleService) {
        this.titleService = titleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTitleById(@PathVariable String id) {
        return new RestCallHandler<>().execute(() -> titleService.getTitleById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTitles_EmptyPath() {
        return getAllTitles();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTitles_RootPath() {
        return getAllTitles();
    }


    private ResponseEntity<?> getAllTitles() {
        return new RestCallHandler<>().execute(titleService::getAllTitles);
    }


}
