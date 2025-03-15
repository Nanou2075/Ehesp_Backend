package com.elearning.elearning.student;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.account.AccountRepository;
import com.elearning.elearning.common.CommService;
import com.elearning.elearning.common.PageResponse;
import com.elearning.elearning.exception.AlreadyExistException;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.document.DocumentService;
import com.elearning.elearning.training.Training;
import com.elearning.elearning.verification.VerificationService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.exception.enums.Permission.STUDENT;
import static com.elearning.elearning.messages.AccountMessage.*;


@Transactional
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final LocalService localService;
    private final DocumentService documentService;
    private final CommService commService;
    private final ModelMapper modelMapper;
    private final VerificationService verificationService;


    /**
     * @param student is the body of My addStudent Request
     */
    @Override
    public void addStudent(MultipartFile cv, MultipartFile degree, Student student) throws MessagingException, IOException {
        /**
         *  check Student to save ;
         */
        Optional.ofNullable(accountRepository.findByPhoneOrMailIgnoreCase(student.getPhone(), student.getMail())).ifPresent(
                (accountEntity) -> {
                    throw new AlreadyExistException(localService.getMessage(USER_EXIT));
                });
        Account account = modelMapper.map(student, Account.class);
        account.setPassword(passwordEncoder.encode(commService.password()));
        account.setPermission(STUDENT);
        accountRepository.save(account);
        studentRepository.save(student);
        documentService.uploadCV(cv, account);
    }


    /**
     * @param id is the param for our request to get Student
     * @return customer is the value return
     */

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND)));
    }

    /**
     * @param id is the param for our request to get Student
     * @return customer is the value return
     */

    @Override
    public Student validStudentBy(String id)  {
        studentRepository.findById(id).ifPresent(
                student -> {
                    String password = commService.password();
                    Account account = accountRepository.findByPhoneOrMailIgnoreCase(student.getPhone(), student.getMail());
                    account.setPassword(passwordEncoder.encode(password));
                    account.setAvailable(true);
                    accountRepository.save(account);
                    studentRepository.save(student);
                    try {
                        verificationService.verificationCode(account,password);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }

                }
        );

        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND)));
    }


    /**
     * @param id            is the param for our request to get Student
     * @param studentUpdate the new data for update the existingStudent
     */
    @Override
    public void updateStudent(String id, StudentUpdate studentUpdate) {
        studentRepository.findById(id)
                .map(existingStudentEntity -> updateExistingStudent(existingStudentEntity, studentUpdate))
                .map(studentRepository::save)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND)));
    }


    /**
     * @param existingStudentEntity is the value will be updated by new data
     * @param studentUpdate         the new data for update the existingStudentEntity
     * @return the customer is the return value
     */

    public Student updateExistingStudent(Student existingStudentEntity, StudentUpdate studentUpdate) {
        existingStudentEntity.setMail(studentUpdate.getMail());
        existingStudentEntity.setPhone(studentUpdate.getPhone());
        existingStudentEntity.setFullName(studentUpdate.getFullName());
        return existingStudentEntity;

    }

    /**
     * @param id is the param for our request to get Student for delete
     */
    @Override
    public void deleteStudentById(String id) {
        studentRepository.findById(id)
                .ifPresentOrElse(studentRepository::delete, () -> {
                    throw new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND));
                });

    }

    /**
     * @param page the number of page
     * @param size the number per page
     * @return PageResponse is value i will return content size value of page number
     */

    @Override
    public PageResponse getAllStudent(int page, int size) {
        List<Student> all = studentRepository.findAllByAvailableTrue();
        if (all.isEmpty()) {
            throw new NotFoundException(NO, localService.getMessage(ACCOUNT_EMPTY));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").ascending());
        Page<Student> customers = studentRepository.findAllByAvailableTrue(pageable);
        return PageResponse.builder()
                .content(Arrays.asList(all.toArray()))
                .number(customers.getNumber())
                .size(customers.getSize())
                .totalElements(customers.getTotalElements())
                .totalPages(customers.getTotalPages())
                .first(customers.isFirst())
                .last(customers.isLast())
                .build();
    }




    @Override
    public Response getAllStudentByTraining(Training training) {
        List<Student> students = studentRepository.findAllByTraining(training);
        if (students.isEmpty()) {
            throw new NotFoundException(NO, localService.getMessage(ACCOUNT_EMPTY));
        }
       return new Response(OK,students);
    }

}
