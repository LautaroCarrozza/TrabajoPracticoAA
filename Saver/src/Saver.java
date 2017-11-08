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
    }

    public void save(T element)  {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(element.getSavingFormat());
            bufferedWriter.newLine();

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
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void restart() {
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
        }
        catch (IOException e){

        }
    }
}
