package domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
@Data
public class Dashbord {

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("dashboard")
    @Expose
    public Dashboard dashboard;

}