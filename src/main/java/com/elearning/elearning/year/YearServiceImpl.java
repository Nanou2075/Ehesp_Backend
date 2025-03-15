package com.elearning.elearning.year;

import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.year.YearMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class YearServiceImpl implements YearService {
    private final YearRepository yearRepository;
    private final YearMapper mapper;


    /*
    verification de donnee pour l'enregistrement d'une année
     */
    private void yearSaveChecking(YearRequest year) {
        Year beginYear = yearRepository.findByBeginYear(year.getBeginYear().substring(0, 4));
        LocalDate now = LocalDate.now();
        String date = now.toString();
        String a = year.getBeginYear().substring(0, 4);
        String b = year.getEndYear().substring(0, 4);
        String c = date.substring(0, 4);
        int A = Integer.parseInt(a);
        int B = Integer.parseInt(b);
        int C = Integer.parseInt(c);
        int Year = A - B;


        if (beginYear != null) {
            throw new NotFoundException(NO, YEAR_EXIT);
        }

        if (A >= B) {
            throw new NotFoundException(NO, YEAR_ERROR);
        }
        if (Year > 1) {
            throw new NotFoundException(NO, YEAR_ERROR);

        }

        if (Year < -1) {
            throw new NotFoundException(NO, YEAR_ERROR);

        }
        year.setBeginYear(year.getBeginYear().substring(0, 4));
        year.setEndYear(year.getEndYear().substring(0, 4));
        year.setCurrentYear(a + "-" + b);
        Year entity = mapper.toEntity(year);
        List<Year> all = yearRepository.findAll();

        if (!all.isEmpty()) {
            all.forEach(yearEntity -> {
                yearEntity.setEnabled(false);
                yearRepository.save(yearEntity);
            });
        }
        yearRepository.save(entity);
    }


    /*
    verification de donnee pour la modification d'une année
     */
    private void yearUpdateChecking(String id, YearRequest year) {
        Year yearId = yearRepository.findById(id).orElseThrow(() -> new NotFoundException(NO, YEAR_NO_EXIT));

        String date = LocalDate.now().toString();
        String a = year.getBeginYear().substring(0, 4);
        String b = year.getEndYear().substring(0, 4);
        String c = date.substring(0, 4);
        int A = Integer.parseInt(a);
        int B = Integer.parseInt(b);
        int C = Integer.parseInt(c);
        int Year = A - B;

        if (A > C) {
            throw new NotFoundException(NO, YEAR_ERROR);

        }


        if (A >= B) {
            throw new NotFoundException(NO, YEAR_ERROR);
        }
        if (Year > 1) {
            throw new NotFoundException(NO, YEAR_ERROR);

        }


        if (Year < -1) {
            throw new NotFoundException(NO, YEAR_ERROR);

        }
        yearId.setBeginYear(year.getBeginYear().substring(0, 4));
        yearId.setEndYear(year.getEndYear().substring(0, 4));
        yearId.setCurrentYear(a + "-" + b);
        yearRepository.save(yearId);
    }


    /*
     l'enregistrement d'une année
     */
    @Override
    public Response save(YearRequest year) {
        yearSaveChecking(year);
        return new Response(OK, YEAR_SAVE);

    }


    /*
    modification  d'une année
     */
    @Override
    public Response update(String id, YearRequest year) throws NotFoundException {
        yearUpdateChecking(id, year);
        return new Response(OK, YEAR_UPDATE);
    }


    /*
   recuperation d'une année par id
     */
    @Override
    public Response get(String id) throws NotFoundException {
        Year anne = yearRepository.findById(id).orElseThrow(() -> new NotFoundException(NO, YEAR_NO_EXIT));
        return new Response(OK, anne);
    }


    /*
   recuperation de la liste des années
     */
    @Override
    public Response getAll() throws NotFoundException {
        List<Year> all = yearRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException(NO, YEAR_EMPTY);
        }
        return new Response(OK, all);
    }


    /*
  suppression  d'une année par id
    */
    @Override
    public Response delete(String id) throws NotFoundException {
        Set<Integer> endYear =new HashSet<>();
        Year byId = yearRepository.findById(id).orElseThrow(() -> new NotFoundException(NO, YEAR_NO_EXIT));
        yearRepository.deleteById(byId.getId());
        List<Year> years = yearRepository.findAll();
      if (!years.isEmpty()) {
          years .forEach(year -> {
              endYear.add(Integer.valueOf(year.getEndYear()));
          });
          int maxYear = endYear.stream().max(Integer::compareTo).get();
          Year max = yearRepository.findByEndYear(String.valueOf(maxYear));
          max.setEnabled(true);
          yearRepository.save(max);
      }
        return new Response(OK, YEAR_DELETE);
    }


}
