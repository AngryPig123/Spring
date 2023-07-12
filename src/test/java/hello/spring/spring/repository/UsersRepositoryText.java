package hello.spring.spring.repository;

import hello.spring.spring.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

import static hello.spring.spring.entity.Grade.VIP;

@Transactional
@SpringBootTest
@Rollback(false)
public class UsersRepositoryText {

    @Autowired
    UsersRepository usersRepository;


    @Test
    @DisplayName("유저_저장_테스트")
    void 유저_저장_테스트() {

        Users saveUser = new Users("홍길동", VIP);
        usersRepository.save(saveUser);

        Optional<Users> findUser = usersRepository.findById(saveUser.getId());
        Assertions.assertNotNull(findUser);
        System.out.println(findUser.get());

    }


}
