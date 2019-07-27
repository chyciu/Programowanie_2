package cats;

import com.google.gson.annotations.SerializedName;

public class CatJson {

    //adnotacja, dzieki której Gson szuka w pola o nazwie "file".
    // dzieki temu możemy nadać polu String bardziej adekwatną, czytelną nazwę.
    @SerializedName("file")
    public String catUrl;


}
