import org.json.simple.JSONObject;

public interface PersistencyService {

    public void save(String path, int rounds, int score, String url, String word);

    public JSONObject read(String path);
}
