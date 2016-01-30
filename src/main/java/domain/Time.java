package domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
@Data
public class Time {

    @SerializedName("from")
    @Expose
    public String from;
    @SerializedName("to")
    @Expose
    public String to;

}