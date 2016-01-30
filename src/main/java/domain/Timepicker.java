package domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
@Data
public class Timepicker {

    @SerializedName("refresh_intervals")
    @Expose
    public List<String> refreshIntervals = new ArrayList<String>();
    @SerializedName("time_options")
    @Expose
    public List<String> timeOptions = new ArrayList<String>();

}