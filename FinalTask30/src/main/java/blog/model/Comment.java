package blog.model;

public class Comment {
    //@CreatedBy
    //@LastModifiedBy
    private Integer id;
    private Integer postId;
    private Integer authorId;
    private String text;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }
}
