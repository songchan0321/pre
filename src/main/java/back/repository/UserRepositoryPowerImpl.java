package back.repository;


import back.entitiy.Identification;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryPowerImpl implements UserRepositoryPower{

    private final JPAQueryFactory jpaQueryFactory;
    private final IdentificationRepository identificationRepository;

    @Override
    @Transactional
    public void addAuthorities(List<Identification> authorities) {

    }

}