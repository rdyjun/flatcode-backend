package bros.flatcode.service;

import bros.flatcode.entity.Member;
import bros.flatcode.entity.Role;
import bros.flatcode.global.error.ErrorCode;
import bros.flatcode.global.error.exception.AlreadyExistsException;
import bros.flatcode.global.error.exception.EntityNotFoundException;
import bros.flatcode.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * Email 중복 확인
     * @param email
     */
    private void validateDuplicated(String email) {

        Optional<Member> findMember = memberRepository.findByEmail(email);

        if (findMember.isPresent())
            throw new AlreadyExistsException(ErrorCode.ALREADY_REGISTERED_MEMBER);
    }

    /**
     * 회원가입
     * @param username
     * @param email
     * @param password
     * @param age
     * @param address
     * @return userId
     */
    @Transactional
    public Long join(String username, String email, String password, Integer age, String address){

        validateDuplicated(email);

        Member newMember = Member.builder()
                .username(username)
                .email(email)
                .password(password)
                .age(age)
                .address(address)
                .role(Role.COMMON)
                .build();

        memberRepository.save(newMember);

        return newMember.getId();
    }

    /**
     * Member 단일 조회
     * @param id
     * @return Member
     */
    public Member findById(Long id){

        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_EXISTS));
    }

    /**
     * Member 전체 조회
     * @return List
     */
    public List findAll() {

        return memberRepository.findAll();
    }

    /**
     * 멤버 username 수정
     * @param id
     * @param username
     * @return
     */
    @Transactional
    public Long updateUsername(Long id, String username){

        Member findMember = findById(id);

        return findMember.updateUsername(username);
    }

    /**
     * 멤버 삭제
     * @param id
     * @return
     */
    @Transactional
    public Boolean deleteMember(Long id){

        Member findMember = findById(id);

        memberRepository.delete(findMember);

        return true;
    }
}
