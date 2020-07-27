package ru.betry;

import java.util.ArrayList;
import java.util.List;

class User {


    private String login;
    private String password;
    private List<Post> postList;

    User() {
        postList = new ArrayList<>();
    }
    User(String login, String password) {
        postList = new ArrayList<>();
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void addPost(Post post) {
        this.postList.add(post);
    }
}