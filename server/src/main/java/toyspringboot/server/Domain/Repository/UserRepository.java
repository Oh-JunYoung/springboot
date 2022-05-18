package toyspringboot.server.Domain.Repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    default User updateByEmail(UserDto userDto) {
        User user = findByEmail(userDto.getEmail()).orElseThrow();
        user.setNotNull(userDto);
        return user;
    }
}
