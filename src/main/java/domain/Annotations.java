package domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
@Data
public class Annotations {

    @SerializedName("list")
    @Expose
    public List<Object> list = new ArrayList<Object>();

}