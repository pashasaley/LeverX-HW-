package task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Work {
    private List<String> fixedKeys;
    private List<String> values;
    private Map<String, String> data;
    private List<String> listOfFiles;

    public Work(){
        data = new HashMap<>();
        listOfFiles = new ArrayList<>();
    }

    public String[] readLines(String name) throws IOException {
        File file = new File(name);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        values = new ArrayList<>();

        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                values.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }

        if (name.equals("src/Data.properties")){//fix keys
            fixedKeys = new ArrayList<>();
            values = new ArrayList<>();
            for (String l : lines.toArray(new String[0])) {
                fixedKeys.add(l.substring(0, l.indexOf("=")).toLowerCase().replace("_", "."));
                values.add(l.substring(l.indexOf("=") + 1));
            }
        }
        return lines.toArray(new String[0]);
    }

    public void fixKeyInFiles() throws IOException {
        Files.walk(Paths.get("./"))//get files of a project
                .map(Path::toFile)
                .forEach(f -> {
                    if (!f.isDirectory()) {
                        listOfFiles.add(f.getAbsolutePath());
                    }
                });

        for (String i : listOfFiles){
            if (i.contains(".java")){
                String[] linesInFile = readLines(i);
                FileWriter fileWriter = new FileWriter(i);
                try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    for (String line : linesInFile) {
                        Pattern pattern = Pattern.compile("([A-Z]+_[A-Z]+_?)+");
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            if (fixedKeys.contains(line.substring(matcher.start(), matcher.end() + 1).
                                    toLowerCase().replace("_", "."))) {
                                line = matcher.replaceAll(line.substring(matcher.start(), matcher.end()).
                                        toLowerCase().replace("_", "."));
                            }
                        }
                        bufferedWriter.write(line + System.getProperty("line.separator"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
//DDFGD_GDFGDFG_DGFGDFGD_GVF - won't change cause file *.properties doesn't contain this key

    public void toFile() throws IOException {
        for (int i = 0; i < values.size(); i++){
            data.put(fixedKeys.get(i), values.get(i));
        }

        FileWriter fileWriter = new FileWriter("src/fixedData.properties");

        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Map.Entry entry : data.entrySet()) {
                bufferedWriter.write(entry + System.getProperty("line.separator"));
                System.out.println(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
