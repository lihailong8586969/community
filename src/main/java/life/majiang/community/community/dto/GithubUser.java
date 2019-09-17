package life.majiang.community.community.dto;

/**
 * @author lihailong
 * @date 2019/9/17 20:33
 */
public class GithubUser {
    private  String name;
    private Long id;
    private  String bio;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
