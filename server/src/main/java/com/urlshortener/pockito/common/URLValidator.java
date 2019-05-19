package com.urlshortener.pockito.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class URLValidator {
    public static final URLValidator INSTANCE = new URLValidator();
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/|www\\.)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    private static final String URL_REGEX1 = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)\n";
    private static final String HTTP_REGEX = "^https?:\\/\\/(.*)";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
    private static final Pattern HTTP_PATTERN = Pattern.compile(HTTP_REGEX);
    private static final Pattern URL_PATTERN1 = Pattern.compile(URL_REGEX1);

    private URLValidator() {
    }

    public boolean validateURL(String url) {
        Matcher m = URL_PATTERN1.matcher(url);
        Matcher m1 = URL_PATTERN.matcher(url);
        return m.matches() || m1.matches();
    }
    public boolean checkIfStartsWith(String url) {
        Matcher m = HTTP_PATTERN.matcher(url);
        return m.lookingAt();
    }
}
