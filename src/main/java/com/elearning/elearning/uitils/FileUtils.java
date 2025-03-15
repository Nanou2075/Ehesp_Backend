package com.elearning.elearning.uitils;


import java.nio.file.Paths;

public class FileUtils {
    public static final String FOLDER_VIDEO_PATH= String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","Video"));
    public static final String FOLDER_BOOK_PATH=String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","book","%s"));
    public static final String FOLDER_PODCAST_PATH=String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","podcast","%s"));
    public static final String FOLDER_DOCUMENT_PATH=String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","document","%s"));


    public static final String URL_DOCUMENT="/api/document/";
    public static final String URL_VIDEO="/api/video/";
    public static final String URL_PODCAST="/api/podcast/";
    public static final String URL_BOOK="/api/book/";





}
