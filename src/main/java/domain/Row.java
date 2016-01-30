package domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
@Data
public class Row {

    @SerializedName("collapse")
    @Expose
    public Boolean collapse;
    @SerializedName("editable")
    @Expose
    public Boolean editable;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("panels")
    @Expose
    public List<Object> panels = new ArrayList<Object>();
    @SerializedName("title")
    @Expose
    public String title;

}