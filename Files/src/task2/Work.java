package task2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Work {
    private List<String> lines;
    private List<String> fixed;
    private File file;
    private HashMap<String, String> data;

    Work(){
        String filename = "src/Data.txt";
        file = new File(filename);
        data = new HashMap<>();
    }

    void readLines() throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        lines = new ArrayList<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        lines.toArray(new String[0]);
    }

    void fix() {
        fixed = new ArrayList<>();
        for (String line : lines) {
            int ind = line.indexOf("=");
            String temp1 = line.substring(0, ind);
            String temp2 = temp1.toLowerCase();
            String temp3 = temp2.replace("_", ".");
            fixed.add(temp3);
            //OR fixed.add(line.substring(0, line.indexOf("=")).toLowerCase().replace("_", "."));
        }
    }

    void toFile() throws IOException {
        for (int i = 0; i < lines.size(); i++){
            String key = fixed.get(i);
            int ind = lines.get(i).indexOf("=")+1;
            String value = lines.get(i).substring(ind);
            data.put(key, value);
            //OR data.put(fixed.get(i), lines.get(i).substring(lines.get(i).indexOf("=")+1));
        }

        try {
        FileWriter fileWriter = new FileWriter("src/fixedData.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(HashMap.Entry entry: data.entrySet()){
            bufferedWriter.write(entry + System.getProperty("line.separator"));
            System.out.println(entry);
        }

        bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
