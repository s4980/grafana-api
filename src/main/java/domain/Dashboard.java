package domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
@Data
public class Dashboard {

    @SerializedName("annotations")
    @Expose
    public Annotations annotations;
    @SerializedName("editable")
    @Expose
    public Boolean editable;
    @SerializedName("hideControls")
    @Expose
    public Boolean hideControls;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("links")
    @Expose
    public List<Object> links = new ArrayList<Object>();
    @SerializedName("originalTitle")
    @Expose
    public String originalTitle;
    @SerializedName("rows")
    @Expose
    public List<Row> rows = new ArrayList<Row>();
    @SerializedName("schemaVersion")
    @Expose
    public Integer schemaVersion;
    @SerializedName("sharedCrosshair")
    @Expose
    public Boolean sharedCrosshair;
    @SerializedName("style")
    @Expose
    public String style;
    @SerializedName("tags")
    @Expose
    public List<Object> tags = new ArrayList<Object>();
    @SerializedName("templating")
    @Expose
    public Templating templating;
    @SerializedName("time")
    @Expose
    public Time time;
    @SerializedName("timepicker")
    @Expose
    public Timepicker timepicker;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("version")
    @Expose
    public Integer version;

}