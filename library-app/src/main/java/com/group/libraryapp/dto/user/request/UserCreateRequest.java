package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; // null 을 표시할 수 있는 정수형

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
