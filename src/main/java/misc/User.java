package misc;

import lombok.Builder;
import org.apache.log4j.Logger;

@Builder
public class User {

    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String country;

    private static Logger LOG = Logger.getLogger(User.class);

    public static void main(String[] args) {

        User user = User.builder()
                .city("Wrocław")
                .name("Filip")
                .build();
    }

    public void updateName(String name){

        //LOG.info();
        this.name = name;
    }
}
