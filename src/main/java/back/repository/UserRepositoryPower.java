package back.repository;

import back.entitiy.Identification;

import java.util.List;

public interface UserRepositoryPower {
    void addAuthorities(List<Identification> authorities);
}
