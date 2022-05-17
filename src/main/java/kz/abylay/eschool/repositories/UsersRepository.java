package kz.abylay.eschool.repositories;

import kz.abylay.eschool.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
