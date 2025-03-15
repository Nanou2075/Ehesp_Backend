package com.elearning.elearning.i18n;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalService {
        private  final MessageSource messageSource;



        @Resource(name = "localHolder")
        LocalHolder localHolder;

        public String getMessage(String code, String... args){
            return messageSource.getMessage(code, args, localHolder.getCurrentLocale());
        }

    }

