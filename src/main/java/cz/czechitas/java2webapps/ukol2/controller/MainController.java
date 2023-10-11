package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {
    private final Random random = new Random();
    private final int NUM_OF_ITEMS = 8;
    private final String FILE_NAME = "citaty.txt";

    private static List<String> readAllLines(String resource)throws IOException {
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){
            return reader.lines().collect(Collectors.toList());
        }
    }

    @GetMapping("/")
    public ModelAndView textAndPicture() throws IOException {
        int randomNumPicture = random.nextInt(NUM_OF_ITEMS) + 1;
        int listSize = readAllLines(FILE_NAME).size();
        int randomNumText = random.nextInt(listSize) + 1;
        ModelAndView result = new ModelAndView("index");
        result.addObject("picture", String.format("/images/picture%d.jpg", randomNumPicture));
        result.addObject("text", readAllLines(FILE_NAME).get(randomNumText - 1));
        return result;
    }
}
