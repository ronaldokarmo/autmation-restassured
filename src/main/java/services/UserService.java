package services;

import lombok.Getter;
import lombok.Setter;

import static support.context.Context.rest;

@Getter
@Setter
public class UserService extends BaseService {

    private String id;

    public void prepare() {
        BASEURI = USER;
        setId("0");
        rest().newRequest();
        rest().header("Content-Type","application/json");
        rest().header("Accept","application/json");
    }

}
