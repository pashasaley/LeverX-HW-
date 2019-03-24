package blog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Article {
    //@CreatedBy
    //@LastModifiedBy
    public Integer id;
    public Integer authotId;
    @NotNull
    public enum Status {PUBLIC, DRAFT};
    @NotNull
    @Size(min=2, max=30)
    private String title;

    @NotNull
    @Size(min=2, max=300)
    private String text;

    @NotNull
    private Status status;

    public Integer getId() {
        return id;
    }

    public Integer getAuthotId() {
        return authotId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthotId(Integer authotId) {
        this.authotId = authotId;
    }

    public String getTitle(){
        return title;
    }

    public String getText(){
        return text;
    }

    public Status getStatus(){
        return status;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setStatus(Status status){
        this.status = status;
    }
}
