import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saver <T extends Saveable> {

    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    private FileReader fileReader;
    private BufferedReader bufferedReader;

    private String fileName;

    public Saver (String fileName)  {
        this.fileName = fileName;
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.close();
        }
        catch (IOException e){
        }
    }

    public void save(T element)  {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
           if (!get().contains(element)) {
               bufferedWriter.write(element.getSavingFormat());
               bufferedWriter.newLine();
           }
            bufferedWriter.close();
        }
        catch (IOException e){
        }
    }

    public List<String> get()  {
        List<String> result = new ArrayList<>();
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
            bufferedReader.close();

        }
        catch (IOException e){
        }
        return result;
    }

    public void remove(T element){
        if (get().contains(element)) {
            List<String> previous = get();
            previous.remove(element);

            for (String line : previous) {
                try {
                    fileWriter = new FileWriter(fileName, true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(element.getSavingFormat());
                    bufferedWriter.newLine();

                    bufferedWriter.close();
                } catch (IOException e) {
                }
            }
        }
        }

}
