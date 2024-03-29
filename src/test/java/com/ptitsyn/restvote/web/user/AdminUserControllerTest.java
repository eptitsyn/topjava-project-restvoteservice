package com.ptitsyn.restvote.web.user;

import com.ptitsyn.restvote.model.Role;
import com.ptitsyn.restvote.model.User;
import com.ptitsyn.restvote.repository.UserRepository;
import com.ptitsyn.restvote.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.ptitsyn.restvote.web.user.UniqueMailValidator.EXCEPTION_DUPLICATE_EMAIL;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminUserControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminUserController.REST_URL + '/';

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 2))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.USER_MATCHER.contentJson(UserTestData.admin));
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + UserTestData.NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void getByEmail() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by-email?email=" + UserTestData.admin.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.USER_MATCHER.contentJson(UserTestData.admin));
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + 1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(userRepository.findById(1).isPresent());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + UserTestData.NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void enableNotFound() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + UserTestData.NOT_FOUND)
                .param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = "user@yandex.ru")
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void update() throws Exception {
        User updated = UserTestData.getUpdated();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(REST_URL + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserTestData.jsonWithPassword(updated, "newPass")))
                .andDo(print())
                .andExpect(status().isNoContent());

        UserTestData.USER_MATCHER.assertMatch(userRepository.getExisted(1),
                UserTestData.getUpdated());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void createWithLocation() throws Exception {
        User newUser = UserTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserTestData.jsonWithPassword(newUser, "newPass")))
                .andExpect(status().isCreated());

        User created = UserTestData.USER_MATCHER.readFromJson(action);
        int newId = created.id();
        newUser.setId(newId);
        UserTestData.USER_MATCHER.assertMatch(created, newUser);
        UserTestData.USER_MATCHER.assertMatch(userRepository.getExisted(newId), newUser);
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.USER_MATCHER.contentJson(UserTestData.usersSorted));
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void enable() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + 1)
                .param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(userRepository.getExisted(1).isEnabled());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void createInvalid() throws Exception {
        User invalid = new User(null, null, "", "newPass", Role.USER, Role.ADMIN);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserTestData.jsonWithPassword(invalid, "newPass")))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void updateInvalid() throws Exception {
        User invalid = new User(UserTestData.user);
        invalid.setName("");
        perform(MockMvcRequestBuilders.put(REST_URL + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserTestData.jsonWithPassword(invalid, "password")))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = "admin@gmail.com")
    void updateHtmlUnsafe() throws Exception {
        User updated = new User(UserTestData.user);
        updated.setName("<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.put(REST_URL + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserTestData.jsonWithPassword(updated, "password")))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @WithUserDetails(value = "admin@gmail.com")
    void updateDuplicate() throws Exception {
        User updated = new User(UserTestData.user);
        updated.setEmail("admin@gmail.com");
        perform(MockMvcRequestBuilders.put(REST_URL + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserTestData.jsonWithPassword(updated, "password")))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString(EXCEPTION_DUPLICATE_EMAIL)));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @WithUserDetails(value = "admin@gmail.com")
    void createDuplicate() throws Exception {
        User expected = new User(null, "New", "user@yandex.ru", "newPass", Role.USER, Role.ADMIN);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserTestData.jsonWithPassword(expected, "newPass")))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString(EXCEPTION_DUPLICATE_EMAIL)));
    }
}
