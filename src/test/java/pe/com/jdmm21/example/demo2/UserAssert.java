package pe.com.jdmm21.example.demo2;

import org.assertj.core.api.AbstractAssert;
import pe.com.jdmm21.example.demo2.persistence.UserEntity;

public class UserAssert extends AbstractAssert<UserAssert, UserEntity> {
    public UserAssert(UserEntity userEntity) {
        super(userEntity, UserAssert.class);
    }

    static UserAssert assertThat(UserEntity actual) {
        return new UserAssert(actual);
    }

    UserAssert hasRegistrationDate() {
        isNotNull();
        if (actual.getRegistrationDate() == null) {
            failWithMessage("Expected user to have a registration date, but it was null");
        }
        return this;
    }

    UserAssert hasTheCorrectName(String name) {
        isNotNull();
        if (!actual.getName().equals(name)) {
            failWithMessage("Name is not correct");
        }
        return this;
    }
}
