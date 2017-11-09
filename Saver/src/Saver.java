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

            List<String> previousList = new ArrayList<>();
            previousList.addAll(get());

            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            if (previousList.contains(element.getSavingFormat())){}
            else {
                previousList.add(element.getSavingFormat());
            }

            for (String line:previousList                 ) {
                bufferedWriter.write(line);
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

    public void restart(){
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedWriter = new BufferedWriter(fileWriter);
    }

}
