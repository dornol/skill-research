package com.neohoon.enverstest.app.repository;

import com.neohoon.enverstest.app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
