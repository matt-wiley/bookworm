package com.wileymab.bookworm.api.interfaces;

import com.wileymab.bookworm.api.model.Title;

import java.util.List;

public interface TitleServiceInterface {

    Title getTitleById(String id);

    List<Title> getAllTitles();

    List<Title> findAllWhereTitleContains(String substring);
    List<Title> findAllWhereAuthorIdEquals(String substring);

}
