package com.urlshortener.pockito.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class URLValidator {
    public static final URLValidator INSTANCE = new URLValidator();
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    private static final String HTTP_REGEX = "^https?:\\/\\/(.*)";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
    private static final Pattern HTTP_PATTERN = Pattern.compile(HTTP_REGEX);

    private URLValidator() {
    }

    public boolean validateURL(String url) {
        Matcher m = URL_PATTERN.matcher(url);
        return m.matches();
    }
    public boolean checkIfStartsWith(String url) {
        Matcher m = HTTP_PATTERN.matcher(url);
        return m.lookingAt();
    }
}
