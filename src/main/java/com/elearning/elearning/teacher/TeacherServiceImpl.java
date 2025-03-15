package com.elearning.elearning.teacher;


import com.elearning.elearning.common.PageResponse;
import com.elearning.elearning.document.DocumentService;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.teacher.TeacherMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final DocumentService documentService;







     /*
    Enregistrement  de l'teacher
     */

    @Override
    public Response save(MultipartFile cv,Teacher teacher) throws IOException {
        teacherRepository.save(teacher);
        documentService.uploadTeacherCV(cv,teacher);

        return new Response(OK, TEACHER_SAVE);
    }


    /*
  Modification  de teacher
   */
    @Override
    public Response update(String id, Teacher value) {
        Teacher teacher = teacherRepository.findById(id);
        teacher.setPhone(value.getPhone());
        teacher.setFullName(value.getFullName());
        teacherRepository.save(teacher);
        return new Response(OK, TEACHER_UPDATE);
    }

      /*
    Suppression  de l'teacher
     */

    @Override
    public Response delete(String id) {
        Teacher teacher = teacherRepository.findById(id);
        if (teacher == null) {
            throw new NotFoundException(NO, TEACHER_NO_EXIT);
        }
        teacherRepository.delete(teacher);
        return new Response(OK, TEACHER_DELETE);
    }


    /*
  recuperation par id  de l'teacher
   */

//    @Cacheable(value = "Teacher")
    @Override
    public Response get(String id) {
        Teacher teacher = teacherRepository.findById(id);
        if (teacher == null) {
            throw new NotFoundException(NO, TEACHER_NO_EXIT);
        }
        return new Response(OK, teacher);
    }


    /*
  recuperation de la liste   des teachers
   */

    //    @Cacheable(value = "Teacher")
    @Override
    public Response getAll(int page, int size) {
        List<Teacher> teacherList = teacherRepository.findAll();
        if (teacherList.isEmpty()) {
            throw new NotFoundException(NO, TEACHER_EMPTY);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());
        Page<Teacher> teachers = teacherRepository.findAll(pageable);
        PageResponse<?> pageResponse = PageResponse.builder()
                .content(Arrays.asList(teacherList.toArray()))
                .number(teachers.getNumber())
                .size(teachers.getSize())
                .totalElements(teachers.getTotalElements())
                .totalPages(teachers.getTotalPages())
                .first(teachers.isFirst())
                .last(teachers.isLast())
                .build();
        return new Response(OK, pageResponse);
    }


    @Override
    public Response search(int page, int size, String keyword) {
        List<Teacher> teacherList = teacherRepository.searchTeacher(keyword);
        if (teacherList.isEmpty()){
            throw new NotFoundException(NO,SEARCH_EMPTY);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());
        Page<Teacher> teachers = teacherRepository.search(keyword,pageable);

        PageResponse<?> pageResponse = PageResponse.builder()
                .content(Arrays.asList(teacherList.toArray()))
                .number(teachers.getNumber())
                .size(teachers.getSize())
                .totalElements(teachers.getTotalElements())
                .totalPages(teachers.getTotalPages())
                .first(teachers.isFirst())
                .last(teachers.isLast())
                .build();
        return new Response(OK, pageResponse);
    }
}
