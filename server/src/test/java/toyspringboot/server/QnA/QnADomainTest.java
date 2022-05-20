package toyspringboot.server.QnA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.QnARepository;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestModule.DomainTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static toyspringboot.server.QnA.QnATestConstants.*;
import static toyspringboot.server.QnA.QnATestConstants.Create_Date;
import static toyspringboot.server.QnA.QnATestConstants.Creator_Member;
import static toyspringboot.server.User.UserTestConstants.*;

public class QnADomainTest  extends DomainTest {
    @Autowired
    private QnARepository qnARepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("[Domain] QnA 생성 테스트")
    public void createTest() throws Exception {
        // given
        User user = userRepository.findByEmail(Exist_User_email).get();

        QnA qnA = QnA.builder()
                .title(QnA_title)
                .isDeleted(QnA_isDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .user(user)
                .build();

        // when
        QnA newQnA = (QnA) test(qnA, qnARepository, "save");

        // then
        assertEquals(qnA.getTitle(), newQnA.getTitle());
        assertEquals(user.getQnAs().get(QnA_Index).getId(), newQnA.getId());
    }
}
