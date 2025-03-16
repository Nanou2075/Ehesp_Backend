package com.elearning.elearning.uitils;


import java.nio.file.Paths;

public class FileUtils {
    public static final String FOLDER_VIDEO_PATH= String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","video"));
    public static final String FOLDER_BOOK_PATH=String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","book"));
    public static final String FOLDER_PODCAST_PATH=String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","podcast"));
    public static final String FOLDER_DOCUMENT_PATH=String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","document"));
    public static final String FOLDER_COVER_PATH=String.valueOf(Paths.get(System.getProperty("user.home"),"Documents","E-learning","cover"));



    public static final String URL_DOCUMENT="/api/document/";
    public static final String URL_VIDEO="/api/video/";
    public static final String URL_PODCAST="/api/podcast/";
    public static final String URL_BOOK="/api/book/";
    public static final String URL_COVER="/api/cover/";





}
