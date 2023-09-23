package com.example.project02.service;

import com.example.project02.DTO.TokenInfo;
import com.example.project02.entity.Member; // 올바른 Member 클래스를 임포트해야 함
import com.example.project02.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public MemberService(MemberRepository memberRepository, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.memberRepository = memberRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {

        Optional<Member> existingMemberOptional = memberRepository.findById(id);

        if (!existingMemberOptional.isPresent()) {
            throw new EntityNotFoundException("Member with id " + id + " not found");
        }

        return memberRepository.findById(id);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member updatedMember) {
        Optional<Member> existingMemberOptional = memberRepository.findById(id);

        if (!existingMemberOptional.isPresent()) {
            throw new EntityNotFoundException("Member with id " + id + " not found");
        }

        Member existingMember = existingMemberOptional.get();
        existingMember.setName(updatedMember.getName());
        existingMember.setEmail(updatedMember.getEmail());

        return memberRepository.save(existingMember);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    // Add more service methods as needed
}
