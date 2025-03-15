package com.elearning.elearning.i18n;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LocalHolder {
    private Locale currentLocale;
}
