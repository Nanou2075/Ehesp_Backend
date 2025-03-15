package com.elearning.elearning.section;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.section.SectionMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final SectionMapper mapper;

    /*
    Verification des donnee d'enregistrement de la section
     */

    private void checkSaveSection(SectionRequest request) {
        Section section = sectionRepository.findByName(request.getName());
        if (section != null) {
            throw new NotFoundException(NO, SECTION_EXIT);
        }

    }

    /*
   Verification des donnee de modification de la section
    */
    private void checkUpdateSection(String id, SectionRequest request) {

        Section employerId = sectionRepository.findById(id);
        Section employerFound = sectionRepository.findByName(request.getName());

        if (employerId == null) {
            throw new NotFoundException(NO, SECTION_NO_EXIT);
        }
        if (employerFound != null) {
            throw new NotFoundException(NO, SECTION_EXIT);
        }


    }

     /*
    Enregistrement  de la section
     */

    @Override
    public Response save(SectionRequest request) {
        checkSaveSection(request);
        Section section = mapper.toEntity(request);
        sectionRepository.save(section);
        return new Response(OK, SECTION_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, SectionRequest request) {
        checkUpdateSection(id, request);
        Section section = sectionRepository.findById(id);
        section.setName(request.getName());
        sectionRepository.save(section);
        return new Response(OK, SECTION_UPDATE);
    }

    @Override
    public Response search(String keyword) {
        List<Section> sections = sectionRepository.search(keyword);
        if (sections.isEmpty()){
            throw new NotFoundException(NO,SECTION_EMPTY);
        }
        return new Response(OK, sections);
    }

      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Section section = sectionRepository.findById(id);
        if (section == null) {
            throw new NotFoundException(NO, SECTION_NO_EXIT);
        }
        sectionRepository.delete(section);
        return new Response(OK, SECTION_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Section section = sectionRepository.findById(id);
        if (section == null) {
            throw new NotFoundException(NO, SECTION_NO_EXIT);
        }
        return new Response(OK, section);
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Section> sections = sectionRepository.findAll(Sort.by("name").ascending());
        if (sections.isEmpty()) {
            throw new NotFoundException(NO, SECTION_EMPTY);
        }
        return new Response(OK, sections);
    }
}
