package com.elearning.elearning.domain;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.domain.DomainMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DomainServiceImpl implements DomainService {
    private final DomainRepository domainRepository;
    private final StudentRepository studentRepository;






     /*
    Enregistrement  de la section
     */

    @Override
    public Response save(Domain domain) {
        domainRepository.save(domain);
        return new Response(OK, DOMAIN_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, Domain domain) {
        domain.setName(domain.getName());
        domainRepository.save(domain);
        return new Response(OK, DOMAIN_UPDATE);
    }

    @Override
    public Response search(String keyword) {
        List<Domain> domains = domainRepository.search(keyword);
        if (domains.isEmpty()){
            throw new NotFoundException(NO,DOMAIN_EMPTY);
        }
        return new Response(OK, domains);
    }

      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Domain domain = domainRepository.findById(id);
        if (domain == null) {
            throw new NotFoundException(NO, DOMAIN_NO_EXIT);
        }
        domainRepository.delete(domain);
        return new Response(OK, DOMAIN_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Domain domain = domainRepository.findById(id);
        if (domain == null) {
            throw new NotFoundException(NO, DOMAIN_NO_EXIT);
        }
        return new Response(OK, domain);
    }



    @Override
    public Set<DomainValue> getDomainStatical() {
        Set<DomainValue> values = new HashSet<>();

        domainRepository.findAll().forEach(domain -> {
            values.add(DomainValue.builder()
                    .name(domain.getName())
                    .number(studentRepository.findAllBySpecialityMentionDomain(domain).isEmpty() ? NO: studentRepository.findAllBySpecialityMentionDomain(domain).size())
                    .build());
        });

        return values;
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Domain> domains = domainRepository.findAll(Sort.by("name").ascending());
        if (domains.isEmpty()) {
            throw new NotFoundException(NO, DOMAIN_EMPTY);
        }
        return new Response(OK, domains);
    }
}
