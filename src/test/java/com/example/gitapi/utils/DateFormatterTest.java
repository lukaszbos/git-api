package com.example.gitapi.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Map;

import static com.example.gitapi.utils.DateFormatter.formatLocalDateTime;

@SpringBootTest
public class DateFormatterTest {

    @Test
    public void givenJsonMapWhenGettingMapThenReturnTrue (){
        LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 22, 20, 13, 10);
        Map<String, Object> jsonRepoMap = Map.ofEntries(
                Map.entry("stargazers_count", "1222"),
                Map.entry("updated_at", "2020-10-22T20:13:10Z")
        );

        Assert.assertEquals(localDateTime, formatLocalDateTime(jsonRepoMap));
    }
}