package com.urlshortener.pockito.common;

import com.fasterxml.jackson.core.Base64Variant;
import org.hashids.Hashids;

import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * A IDConverter encoder/decoder.
 *
 * @author Sebastian Ruhleder, sebastian@seruco.io
 */
public class IDConverter {

    public static final IDConverter INSTANCE = new IDConverter();
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static final int BASE = ALPHABET.length();
    private IDConverter() {
        initializeCharToIndexTable();
        initializeIndexToCharTable();
    }

    private static HashMap<Character, Integer> charToIndexTable;
    private static List<Character> indexToCharTable;

    private void initializeCharToIndexTable() {
        charToIndexTable = new HashMap<>();
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            charToIndexTable.put(c, i);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            charToIndexTable.put(c, i);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            charToIndexTable.put(c, i);
        }
    }

    private void initializeIndexToCharTable() {
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        indexToCharTable = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            indexToCharTable.add(c);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            indexToCharTable.add(c);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            indexToCharTable.add(c);
        }
    }

    public static String createUniqueID(Long id) {
//        Base64.getEncoder().encodeToString(id.toString().getBytes());
//        List<Integer> base62ID = convertBase10ToBase62ID(id);
//        StringBuilder uniqueURLID = new StringBuilder();
//        for (int digit: base62ID) {
//            uniqueURLID.append(indexToCharTable.get(digit));
//        }
        GetBase62(6);
        Hashids hashids = new Hashids("this is my salt");
        String s = hashids.encode(id);
//        return GetBase62(6);
        return s;
    }
    private static char[] _base62chars =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
                    .toCharArray();
    private static Random _random = new Random();

    public static String GetBase62(int length)
    {
        StringBuilder sb = new StringBuilder(length);
        for (int i=0; i<length; i++) {
        sb.append(_base62chars[_random.nextInt(62)]);}

        return sb.toString();
    }

    private static List<Integer> convertBase10ToBase62ID(Long id) {
        Base64.getDecoder();
        List<Integer> digits = new LinkedList<>();
        while(id > 0) {
            int remainder = (int)(id % 62);
            ((LinkedList<Integer>) digits).addFirst(remainder);
            id /= 62;
        }
        return digits;
    }

    public static Long getDictionaryKeyFromUniqueID(String uniqueID) {
        List<Character> base62IDs = new ArrayList<>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62IDs.add(uniqueID.charAt(i));
        }
        Long dictionaryKey = convertBase62ToBase10ID(base62IDs);
        return dictionaryKey;
    }

    private static Long convertBase62ToBase10ID(List<Character> ids) {
        long id = 0L;
        for (int i = 0, exp = ids.size() - 1; i < ids.size(); ++i, --exp) {
            int base10 = charToIndexTable.get(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }

}

