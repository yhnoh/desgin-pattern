package com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.member;

import com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberRepository implements Repository<Member, Long> {

    public Map<Long, Member> members = new HashMap<>();

    public MemberRepository() {
        members.put(1L, new Member(1L, "username1"));
        members.put(2L, new Member(2L, "username2"));
        members.put(3L, new Member(3L, "username3"));
    }

    @Override
    public List<Member> findAll() {
        return members.values().stream().collect(Collectors.toList());
    }

    @Override
    public Member findById(Long id) {
        return members.get(id);
    }
}
