import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONService implements PersistencyService{

    @Override
    public void save(String path, int rounds, int score, String url, String word) {
        JSONObject req = new JSONObject();
        req.put("rounds", rounds);
        req.put("score", score);
        req.put("url", url);
        req.put("word", word);
        try(FileWriter writer = new FileWriter(path)){
            writer.write(req.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject read(String path) {
        JSONObject res = null;
        JSONParser parser = new JSONParser();
        try{
            res = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(path)));
        }
        catch(ParseException | IOException e){
            e.printStackTrace();
        }
        return res;
    }
}
