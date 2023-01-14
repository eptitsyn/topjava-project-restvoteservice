package com.ptitsyn.restvote.web.vote;

import com.ptitsyn.restvote.service.VoteService;
import com.ptitsyn.restvote.to.VoteTo;
import com.ptitsyn.restvote.util.JsonUtil;
import com.ptitsyn.restvote.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.ptitsyn.restvote.web.TestUtil.userHttpBasic;
import static com.ptitsyn.restvote.web.user.UserTestData.admin;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {

    public static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    VoteService voteService;

//    @Test
//    @WithUserDetails(value = "user@yandex.ru")
//    void get() throws Exception {
//        perform(MockMvcRequestBuilders.put(VoteController.REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(new Vote(restaurant1, user, LocalDateTime.now()))))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(USER_MATCHER.contentJson(user));
//    }


    @Test
    void voteWithNoAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 1))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(new VoteTo(VoteTestData.vote1.getRestaurant().id()))))
                .andExpect(status().isCreated());

//        VOTE_MATCHER.assertMatch(voteService.get(updated.id()), updated);
    }
}
