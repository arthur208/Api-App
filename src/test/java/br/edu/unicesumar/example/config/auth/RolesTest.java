package br.edu.unicesumar.example.config.auth;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RolesTest {

    @Test
    void whenRolesHasCompositeRolesThenTxpectedGetCompositeRolesRecursivelyTest() {

        Assertions.assertThat(Roles.ROLE_READ_ONLY.getCompositeRoles())
                .isEmpty();

        Assertions.assertThat(Roles.ROLE_INTERNAL.getCompositeRoles())
                .hasSize(1)
                .containsExactlyInAnyOrder(Roles.ROLE_READ_ONLY);

        Assertions.assertThat(Roles.ROLE_TEST.getCompositeRoles())
                .hasSize(2)
                .containsExactlyInAnyOrder(Roles.ROLE_READ_ONLY, Roles.ROLE_INTERNAL);

        Assertions.assertThat(Roles.ROLE_ADMIN.getCompositeRoles())
                .hasSize(3)
                .containsExactlyInAnyOrder(Roles.ROLE_READ_ONLY, Roles.ROLE_INTERNAL, Roles.ROLE_TEST);
    }

}
