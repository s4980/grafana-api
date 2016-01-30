package domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
@Data
public class Meta {

    @SerializedName("isStarred")
    @Expose
    public Boolean isStarred;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("canSave")
    @Expose
    public Boolean canSave;
    @SerializedName("canEdit")
    @Expose
    public Boolean canEdit;
    @SerializedName("canStar")
    @Expose
    public Boolean canStar;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("expires")
    @Expose
    public String expires;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("updated")
    @Expose
    public String updated;

}